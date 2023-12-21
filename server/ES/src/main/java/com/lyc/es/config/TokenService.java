package com.lyc.es.config;

import com.lyc.es.bean.ResponseMsg;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenService {
    @Value("${token.header}")
    private static String header;
    @Value("${token.expireTime}")
    private static int expireTime;

    @Value("${token.secret}")
    private static String secret;

    private static UUID uuid = UUID.randomUUID();

    private static Key getKeyInstance(){
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        byte[]apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secretKey");
        Key key = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());
        return key;
    }
    public static String createJWT(int UserId){
        SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expireMillis = nowMillis + 30 * 1000*60;
        Date expiryDate = new Date(expireMillis);

        Key key = getKeyInstance();
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(uuid))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("UserId",UserId)
                .claim("username","admin")
                .signWith(signatureAlgorithm, key);
        return builder.compact();
    }

    public static Claims parseJWT(String jwt){
        //签名秘钥，和生成的签名的秘钥一模一样
        Key key = getKeyInstance();
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
        return claims;

    }


    //解析jwt，或者claim实例，通过key来取出相应的值
    public static ResponseMsg parseAndRefreshJWT(String jwt){
        ResponseMsg msg = new ResponseMsg();
        //捕获解析jwt过程中的相关异常
        try {
            Claims c = parseJWT(jwt);
            int UserId = (int) c.get("UserId");
            //解析成功生成新的jwt，期限30分钟，并返回新的jwt与成功信息
            String newJWT = createJWT(UserId);
            System.out.println("新jwt的id: "+c.getId());
            System.out.println("新jwt的签发时间: "+c.getIssuedAt());
            System.out.println("新jwt的到期时间: "+c.getExpiration());
            System.out.println("新jwt的用户的id: "+c.get("UserId"));
            System.out.println("新jwt的用户名: "+c.get("username"));
            System.out.println("新jwt： "+newJWT);
            msg.setStatus(200);
            msg.setMessage("jwt认证成功！");
            //捕获到jwt过期异常，返回相关信息
        }catch (ExpiredJwtException eje){
            msg.setStatus(500);
            msg.setMessage("ExpiredJwtException JWT已过期，请重新登录！");


            //如果伪造了jwt，解析jwt的时候会捕获到jwt不合法错误，并返回相关信息
        }catch (Exception e){
            msg.setStatus(500);
            msg.setMessage("Exception JWT不合法！");

        }
        return msg;
    }
    public static void main(String[] args) {
        String jwt = createJWT(5);
        System.out.println(jwt);
        ResponseMsg msg = parseAndRefreshJWT(jwt);
        System.out.println(msg);
    }
}

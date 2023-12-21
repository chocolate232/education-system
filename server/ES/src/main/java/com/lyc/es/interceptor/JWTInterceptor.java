package com.lyc.es.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyc.es.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        // 获取请求头中令牌
        String token = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(token)) {
            try {
                Claims claims = JWTUtils.parseJwt(token); // 如果找到令牌就使用 JWTUtils.parseJwt() 方法解析令牌
                return true;                                     // 解析成功，即令牌有效，返回true
            } catch (RuntimeException e) {          // 如果解析失败，会捕获 RuntimeException 异常
                e.printStackTrace();
                map.put("msg", e.getMessage());  // map.put("msg","密钥错误!");
            }
        }
        map.put("message","token为null,必须携带token");
        map.put("state",false);             // 设置状态
        // 将map 转为 json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}





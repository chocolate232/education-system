package com.lyc.es.contrller;

import com.lyc.es.bean.EmailDetails;
import com.lyc.es.bean.Menu;
import com.lyc.es.bean.ResponseMsg;
import com.lyc.es.bean.User;
import com.lyc.es.service.EmailService;
import com.lyc.es.service.UserService;
import com.lyc.es.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@Slf4j
public class LycUserController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/login")
    public ResponseMsg<Map> login(@RequestBody User user){
        ResponseMsg<Map> res = new ResponseMsg<Map>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_name",user.getUsername());
        map.put("password",user.getPassword());
        User u =new User();
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());

        System.out.println(u);
        try{
            List<User> users = userService.login(u);
            System.out.println(users);
            if(users.size()!=0){
                System.out.println(users.get(0));
                res.success("登录成功");
                map.put("user",users.get(0));
                map.put("state","200");
                Map<String, Object> payLoad = new HashMap<>();
                payLoad.put("id", u.getId());
                payLoad.put("userName", u.getUsername());
                String token = JWTUtils.getToken(payLoad);
                map.put("token", token);
                res.setData(map);

            }else {
                System.out.println("登录失败");
                map.put("state","400");
                res.fail("登录失败");
                res.setData(map);
            }
        }catch (Exception e){
            res.fail("登录败");

        }
        return res;
        // return  userService.login(map);
    }
    @PostMapping("/auth/register")
    public ResponseMsg<Map> register(@RequestBody User user){
        ResponseMsg<Map> res = new ResponseMsg<Map>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_name",user.getUsername());
        map.put("password",user.getPassword());
        User u =new User();
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        System.out.println(u);
        try{
            int i = userService.register(u);
            System.out.println(i);
            if(i==0){
                res.success("注册成功");
                map.put("state","200");
                res.setData(map);
            } else if (i==1) {
                res.fail("用户名已存在");
                map.put("state","400");
                res.setData(map);
            } else {
                System.out.println("注册失败");
                map.put("state","300");
                res.fail("注册失败");
                res.setData(map);
            }
        }catch (Exception e){
            res.fail("注册失败");

        }
        return res;

    }


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> userMap) {
        // 打印了包含两个参数的日志信息
        log.info("userMap: {}", userMap);
        User u2 =new User();
        u2.setPassword(userMap.get("password").toString());
        u2.setUsername(userMap.get("username").toString());
        // 调用返回List<User>的方法
        List<User> userList = userService.login(u2);
        Map<String, Object> map = new HashMap<>();
        try {
            if (userList != null && !userList.isEmpty()) {
                // 如果列表不为空，表示认证成功
                User u = userList.get(0);  // 假设取第一个用户
                Map<String, Object> payLoad = new HashMap<>();
                payLoad.put("id", u.getId());
                payLoad.put("userName", u.getUsername());
                String token = JWTUtils.getToken(payLoad);
                map.put("state", true);
                map.put("message", "认证成功");
                map.put("token", token);
            } else {
                // 如果列表为空，表示用户名或密码错误
                map.put("state", false);
                map.put("message", "用户名或者密码错误");
            }
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }


    @RequestMapping("/menu")
public List<Menu> queryMenuByRole(String id){
        System.out.println("access menu");
        System.out.println(id);
        return userService.queryMenuByRole(Integer.parseInt(id));
}
    @RequestMapping("/getUser")
    public User getUser(String Name){
        System.out.println(Name);
        return userService.queryUserByName(Name);
    }
@RequestMapping("/List/{Test}")
public List<User> AllList(@PathVariable String Test, HttpServletRequest request){
        User u =new User();
        if (request.getParameter("id")!=null){
            u.setId(Integer.parseInt(request.getParameter("id")));
        }
        u.setName(request.getParameter("name"));
        u.setPassword(request.getParameter("password"));
        u.setUsername(request.getParameter("username"));
    System.out.println("access List");
    System.out.println(request.getParameter("page"));
    switch (Test){
        case("searchUser"):
        return userService.searchAllUser();
//        case ("deleteUser"):
//            return userService.deleteUser(u);
//        case ("updateUser"):
//                return userService.updateUser(u);
//        case ("getUser"):{
//              int page=Integer.parseInt(request.getParameter("page"));
//              return userService.getUserByPage(page,2).toString();
//                }

    }
return null;
//    return "0";
}

    @PostMapping("/addUser")
    public ResponseMsg<Map> addUser(@RequestBody Map<String,Object> map){
        ResponseMsg<Map> res = new ResponseMsg<Map>();
        System.out.println("进入新增角色");
        System.out.println(map);
        try{
            int i = userService.addUser(map);
            System.out.println(i);
            if(i==0){
                res.success("注册成功");
                map.put("state","200");
                res.setData(map);
            } else if (i==1) {
                res.fail("用户名已存在");
                map.put("state","400");
                res.setData(map);
            } else {
                System.out.println("注册失败");
                map.put("state","300");
                res.fail("注册失败");
                res.setData(map);
            }
        }catch (Exception e){
            res.fail("注册失败");

        }
        return res;

    }
    @PostMapping("/searchUser")
    public ResponseMsg<Map> searchUser(@RequestBody Map<String,Object> map){
        ResponseMsg<Map> res = new ResponseMsg<Map>();
        System.out.println("进入查询角色");
        System.out.println(map);
        try{
            List<User> users=userService.searchUser(map);
            if (users==null){
                res.fail("查询失败");
                map.put("state","400");

            }else {
                res.success("查询成功");
                map.put("users",users);
                map.put("state","200");
                res.setData(map);
            }
        }catch (Exception e){
            res.fail("查询失败");
            map.put("error",e);
            res.setData(map);
        }


        return res;

    }

    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }


}

package com.lyc.contrller;

import com.lyc.bean.Menu;
import com.lyc.bean.ResponseMsg;
import com.lyc.bean.User;
import com.lyc.service.UserService;
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

    @RequestMapping("/register")
    public  int register(String username,String password,int id,String name){
        User u =new User();
        u.setId(id);
        u.setName(name);
        u.setPassword(password);
        u.setUsername(username);
        return  userService.register(u);
    }

    @RequestMapping("/login")
    public List<User> login(String username,String password){
        User u =new User();
        u.setPassword(password);
        u.setUsername(username);
        return  userService.login(u);
    }

    @RequestMapping("/menu")
public List<Menu> queryMenuByRole(String id){

        return userService.queryMenuByRole(Integer.parseInt(id));
}

@RequestMapping("/List/{Test}")
public ResponseMsg<Map> AllList(@PathVariable String Test, HttpServletRequest request){
        User u =new User();
    ResponseMsg<Map> res = new ResponseMsg<Map>();
    Map<String,Object> map = new HashMap<String, Object>();
        if (request.getParameter("id")!=null){
            u.setId(Integer.parseInt(request.getParameter("id")));
        }
        u.setName(request.getParameter("name"));
        u.setPassword(request.getParameter("password"));
        u.setUsername(request.getParameter("username"));
    System.out.println(request.getParameter("page"));
    switch (Test){
        case("searchUser"):{
            List<User> users=userService.searchAllUser();
            map.put("user",users);
            res.setData(map);
        return res;
        }
        case ("deleteUser"):
            String users=userService.deleteUser(u);
            map.put("msg",users);
            res.setData(map);
            return res;
//        case ("updateUser"):
//                return userService.updateUser(u);
//        case ("getUser"):{
//              int page=Integer.parseInt(request.getParameter("page"));
//              return userService.getUserByPage(page,2).toString();
//                }



    }

    return null;
}



}

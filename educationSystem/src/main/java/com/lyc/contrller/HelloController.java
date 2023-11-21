package com.lyc.contrller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "aaa";//是返回aaaa当页面还是文本输出
    }

    @RequestMapping("/")
//    public String index(){return "hello";}
    public String index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return "login";
    }
}

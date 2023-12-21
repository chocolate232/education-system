package com.lyc.es.contrller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        System.out.println("sss");
        return "login";
    }
    @RequestMapping("/test")
    public String test(){
        return "hello.html";
    }
    @RequestMapping("/test1")
    public String test1(){
        return "login";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "login.html";
    }

    @RequestMapping("/index2")
    public ModelAndView index2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("men","hello index2");
        mv.setViewName("hello");
        return mv;

    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "a";//是返回aaaa当页面还是文本输出
    }



}

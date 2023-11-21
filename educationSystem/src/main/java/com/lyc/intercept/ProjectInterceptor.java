package com.lyc.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
//定义拦截器类，实现HandlerInterceptor接口
//注意当前类必须受Spring容器控制
public class ProjectInterceptor implements HandlerInterceptor {
    public ProjectInterceptor(){
        System.out.println("-----------------------------------------------------------------------");
    }
    @Override
    //原始方法调用前执行的内容
    //返回值类型可以拦截控制的执行，true放行，false终止
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 獲取 報文裡面的請求行裡的URL
        /*String url = request.getRequestURL().toString();
        if (!url.contains("toLogin")) {
            // 如果用户没有权限访问该URL，可以抛出异常或重定向到错误页面
            response.sendError(HttpServletResponse.SC_FORBIDDEN); // 抛出403异常
            return false;
        }
        System.out.println("preHandle..."+"contentType");*/
        return true;
    }

    @Override
    //原始方法调用后执行的内容
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //  response.setContentType("text/xml");

        System.out.println("postHandle...");
    }

    @Override
    //原始方法调用完成后执行的内容
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // response.setContentType("text/xml");
        //  response.setCharacterEncoding("UTF-8");
        //response.setContentLength(1000);

        System.out.println("afterCompletion...");
    }
}
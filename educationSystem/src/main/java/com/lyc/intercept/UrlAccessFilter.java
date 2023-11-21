package com.lyc.intercept;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/hello"})
public class UrlAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("過濾器初始化------------------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 检查请求的URL
        String url = httpRequest.getRequestURL().toString();
        if (url.contains("hello")) {
            // 如果用户没有权限访问该URL，可以抛出异常或重定向到错误页面
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN); // 抛出403异常
            return;
        }

        // 如果用户有权限访问该URL，继续执行后续的请求处理逻辑
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
package com.lyc.config;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {  //开启spring 容器
        return new Class[]{SpringConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() { //开启子容器 spring mvc 容器
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() { //拦截 请求
        return new String[]{"/"};//代表拦截所有请求，或者改成/*
    }
    //乱码处理，javax.servlet-api
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
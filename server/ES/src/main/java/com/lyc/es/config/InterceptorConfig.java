package com.lyc.es.config;

import com.lyc.es.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[] {"/auth/login","/auth/register","/*.html","/css/**","/js/**","/images/**","/layui/**"};    // 添加不拦截的方法
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")         // 其他接口token验证
                .excludePathPatterns(patterns);  // 不进行token验证
    }
}
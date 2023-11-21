package com.lyc.config;

import com.lyc.aspect.WebLogAspect;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({"com.lyc.contrller","com.lyc.intercept"})

@Import({WebConfig.class, WebLogAspect.class})
@EnableWebMvc

@EnableAspectJAutoProxy
public class SpringMvcConfig {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");///WEB-INF/views/login.jsp
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    public SpringMvcConfig(){
        System.out.println("---------------SpringMvcConfig----------------");
    }

}

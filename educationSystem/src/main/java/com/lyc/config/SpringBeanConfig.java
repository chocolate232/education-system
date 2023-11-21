package com.lyc.config;

import com.lyc.bean.Breakdown;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lyc.bean")
public class SpringBeanConfig {
    @Bean
    public Breakdown getBreakdown(){
        return  new Breakdown();
    }
}

package com.lyc.config;

import com.lyc.aspect.MyAdvice;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import java.io.InputStream;
import java.util.Properties;

@Configuration
@MapperScan("com.lyc.dao") //与下面的注解相似，但是这个是mybatis+spring
@ComponentScan({"com.lyc.service"})//spring

 //使用包扫描创建 Mybatis映射接口的代理对象
@Import({MyAdvice.class})
@EnableAspectJAutoProxy
public class SpringConfig {

   @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();//创建 Properties 对象，用于存储加载的配置属性。
        properties.load(inputStream);
        String resource = "mybatis-config.xml";
        InputStream inputStream2 = Resources.getResourceAsStream(resource);
        //通过配置文件和属性创建 SqlSessionFactory 实例。
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream2,properties);
      // sqlSessionFactory.openSession(true);
        return sqlSessionFactory;
    }

}

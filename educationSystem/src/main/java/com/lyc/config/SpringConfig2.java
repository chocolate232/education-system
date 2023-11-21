package com.lyc.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.lyc.dao")
@PropertySource("classpath:jdbc.properties")
@ComponentScan({"com.lyc.service"})

 //使用包扫描创建 Mybatis映射接口的代理对象
public class SpringConfig2 {


   @Value("${jdbc.driver}")
    private String driverClassName;


    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;


    @Value("${jdbc.password}")
    private String password;
    @Bean
    public DataSource getDataSource() {
        System.out.println("创建数据源");
        DataSource dataSource = new PooledDataSource(
                this.driverClassName,
                this.url,
                this.username,
                this.password
        );
        return dataSource;
    }



    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //设置数据源
        bean.setDataSource(getDataSource());
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/yqb/dao/*.xml");
        bean.setMapperLocations(resources);
        //设置别名
        bean.setTypeAliasesPackage("com.yqb.bean");
        return bean.getObject();
    }
}
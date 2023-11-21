package com.lyc.config;


import com.lyc.intercept.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import java.nio.charset.Charset;
import java.util.List;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectInterceptor projectInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置多拦截器
        registry.addInterceptor(projectInterceptor).addPathPatterns("/", "/*");


    }

    /**
     * /**
     * * 消息内容转换配置
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //字符串
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        // List<MediaType> list = new ArrayList<MediaType>();

        //list.add(MediaType.TEXT_PLAIN); // 支持文本类型，如果需要的话
        // converter.setSupportedMediaTypes(list);
        converter.setDefaultCharset(Charset.defaultCharset());
        converters.add(converter);

        // list.add(MediaType.APPLICATION_JSON); // 支持JSON类型
        //MappingJackson2HttpMessageConverter来处理JSON类型的数据
        MappingJackson2HttpMessageConverter converter2 = new MappingJackson2HttpMessageConverter();
        // converter2.setSupportedMediaTypes(list);
        // converter2.setDefaultCharset(Charset.defaultCharset());
        converters.add(converter2);
    }



/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //配置String类型消息转换器
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        //converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        converter.setDefaultCharset(Charset.defaultCharset());
        converters.add(converter);
      *//*  配置fastJson返回json转换
      //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);*//*

    }*/

    /**
     * 解决跨域问题
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET")
                .allowedOrigins("*");
    }


    /**
     * 配置静态访问资源
     *
     * @Override
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
    }
}
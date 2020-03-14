package com.kaixin8848.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * springboot项目，若打包成war包，使用外置的tomcat启动
 * <p>
 * 1、需要继承 org.springframework.boot.context.web.SpringBootServletInitializer类
 * <p>
 * 2、然后重写configure(SpringApplicationBuilder application)方法
 */
@Configuration
@EnableScheduling//开启基于注解的定时任务
@EnableAsync   //开启基于注解的异步任务
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//该注解的作用是，排除自动注入数据源的配置（取消数据库配置），一般使用在客户端（消费者）服务中
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 设置上传文件大小
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大100M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(100, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小100M
        factory.setMaxRequestSize(DataSize.of(100, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

//    @Bean
//    public Converter<String, Date> addNewConvert() {
//        return source -> {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = null;
//            try {
//                date = sdf.parse(source);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return date;
//        };
//    }
}


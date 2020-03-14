package com.kaixin8848.home.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configurer {
    /**
     * 通过http://localhost:8080/kaixinhome-api/swagger-ui.html访问接口文档
     * 152.136.97.254:8083/kaixinhome-api/swagger-ui.html(kaixin8848.com:8083/kaixinhome-api/swagger-ui.html)
     * http://kaixin8848.com/kaixinhome-api/swagger-ui.html
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kaixin8848.home.web"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("开心之夹平台 API(8083)")
                        .version("1.0")
                        .contact(new Contact("开心之夹开发组", "http://kaixin8848.com", "18361036456@163.com"))
                        .build());
    }
}

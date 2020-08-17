package com.offcn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//开启Swagger2文档
public class SwaggerConfig {

    //配置文档基本信息
    public ApiInfo createApiInfo(){
        return new ApiInfoBuilder().title("这是优就业测试API文档")
                            .version("1.0")
                            .description("优就业")
                            .termsOfServiceUrl("http://www.ujiuye.com")
                            .contact("Sunny")
                            .build();
    }

    //配置Docket文档对象
    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.offcn.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

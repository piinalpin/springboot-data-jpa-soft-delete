package com.piinalpin.customsoftdeletes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.piinalpin.customsoftdeletes.http.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Custom Soft Deletes REST")
                .description("An example for custom soft deletes implementation.")
                .version("1.0.0-SNAPSHOT")
                .contact(new Contact("Maverick", "http://piinalpin.com", "calvinjoe782@gmail.com"))
                .build();
    }
    
}

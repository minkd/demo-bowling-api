package com.mink.demo.bowlingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        String version = "1";

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Bowling Service - Version " + version)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.mink.demo.bowlingapi.api.rest"))
                .paths(PathSelectors.ant("/v" + version + "/**")).build().apiInfo(apiInfo(version));
    }

    private ApiInfo apiInfo(String version) {

        return new ApiInfo("Bowling Service",
                "The Bowling service provide a restful api to manage bowling scorecards",
                version, "http://www.mybowlingsite.com/about-us/privacy-policy",
                new Contact("Bowlers of Austin", "developer.bowling.com", "derrick.mink@gmail.com"),
                "End User License Agreement", "http://www.mybowling.com/about-us/end-user-license-agreement",
                Collections.emptyList());

    }
}

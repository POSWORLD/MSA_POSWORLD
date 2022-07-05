package com.posco.posworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
        public Docket api1() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("user")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posco.posworld.user.controller"))

                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    @Bean
    public Docket api2() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("board")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posco.posworld.board.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket api3() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("photo")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posco.posworld.photo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket api4() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("pComment")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posco.posworld.pComment.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket api5() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("home")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posco.posworld.home.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("posworld")
                    .description("posworld api docs")
                    .version("1.0")
                    .build();
    }
}

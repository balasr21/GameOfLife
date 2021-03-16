package com.api.gameoflife.config;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * The title.
     */
    @Value("${project.title}")
    private String title = "Placing Odds";

    /**
     * The description.
     */
    @Value("${project.description}")
    private String description;

    /**
     * The contact email id.
     */
    @Value("${project.contact.emailid}")
    private String contactEmailId;

    /**
     * The api version.
     */
    @Value("${swagger.api.version}")
    private String apiVersion;

    /**
     * The api contact name.
     */
    @Value("${project.organization.name}")
    private String apiContactName;

    /**
     * The api contact url.
     */
    @Value("${project.organization.url}")
    private String apiContactUrl;

    /**
     * The license.
     */
    @Value("${project.license}")
    private String license;

    /**
     * The license url.
     */
    @Value("${project.license.url}")
    private String licenseUrl;

    /**
     * Api.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfo(
                title,
                description,
                apiVersion,
                null,
                new Contact(
                        apiContactName,
                        apiContactUrl,
                        contactEmailId
                ),
                license,
                licenseUrl,
                Collections.emptyList()
        )).select().paths(PathSelectors.regex("^/odds/.*$")).build();
    }

}



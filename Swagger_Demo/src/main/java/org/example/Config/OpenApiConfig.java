package org.example.Config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Collections;


@Configuration
public class OpenApiConfig {
    static final String SECURITY_SCHEME_NAME = "Bearer oAuth Token";

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().
                addSecurityItem(new SecurityRequirement().
                        addList(SECURITY_SCHEME_NAME)).
                components(new Components().
                        addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme().
                                name(SECURITY_SCHEME_NAME).type(SecurityScheme.Type.HTTP).
                                scheme("bearer").bearerFormat("JWT"))).
                info(new Info().title("SwaggerDemo").version("2.0").
                        description("This Api is the purporse of swagger Demo").termsOfService("Terms of service").
                        license(getLicense()).contact(getContact()));
    }
    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder().group("Api").pathsToMatch("/employee/v1/**").packagesToScan("org.example.Controller").build();
    }
    @Bean
    public GroupedOpenApi groupedOpenApi1(){
        return GroupedOpenApi.builder().group("Api1").pathsToMatch("/getcontact/**").packagesToScan("org.example.DuplicateController").build();
    }
    private static License getLicense(){
        License license=new License();
        license.setName("SwaggerDemoApplication");
        license.setUrl("http://localhost:8080/actuator");
        license.setExtensions(Collections.emptyMap());
        return license;
    }
    private static Contact getContact(){
        Contact contact=new Contact();
        contact.setEmail("anandanumashankar@gmail.com");
        contact.setName("Anandan Umasankar");
        contact.setUrl("http://localhost:8080/employee/v1/contactInfo");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }

}

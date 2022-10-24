package com.rapidtech.projectjava.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class OpenApi30Config {
    //http://localhost:8080/swagger-ui.html
    //http://localhost:8080/v3/api-docs

    private final String moduleName;
    private final String apiVersion;

    public OpenApi30Config(@Value("Spring Boot API") String moduleName,
                           @Value("1.0") String apiVersion){
        this.moduleName = moduleName;
        this.apiVersion = apiVersion;
    }

    @Bean
    public OpenAPI customOpenAPI(){
        final String securityShemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securityShemeName))
                .components(new Components().addSecuritySchemes(securityShemeName,
                        new SecurityScheme().name(securityShemeName).type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title(apiTitle).version(apiVersion));
    }
}

package com.example.jwtvalidator.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("gr.mendes23@gmail.com");
        contact.setName("Igor Mendes");
        contact.setUrl("https://www.linkedin.com/in/igor-mendes-690b4536/");

        Info info = new Info()
                .title("JWT Validator API")
                .version("1.0")
                .contact(contact)
                .description("API responsável por validar as claims de tokens JWT");

        return new OpenAPI().info(info);
    }

}

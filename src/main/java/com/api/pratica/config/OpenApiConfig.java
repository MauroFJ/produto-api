package com.api.pratica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI produtoApiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Produto API")
                        .description("API REST para gerenciamento de produtos: CRUD, validações e busca dinâmica por filtros combináveis (nome, status, faixas de valor e quantidade).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mauro FJ")
                                .url("https://github.com/MauroFJ")));
    }
}


package com.example.book_catalog.config; // Перевір, чи співпадає назва твого пакету!

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Catalog API")
                        .version("1.0")
                        .description("Документація API для управління каталогом книг"));
    }
}
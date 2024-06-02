package config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("API-service").version("V1.0.0"))
                .servers(List.of(new Server().url("http://localhost:8080")));
    }
    @Bean
    public GroupedOpenApi groupedOpenApi(){
    return GroupedOpenApi.builder()
            .group("api-service-1")
            .packagesToScan("endpoint")
            .build();
    }
}

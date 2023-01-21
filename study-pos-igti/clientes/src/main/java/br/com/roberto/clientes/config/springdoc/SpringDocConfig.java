package br.com.roberto.clientes.config.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clientes API")
                        .description("Api de Clientes")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Pos Igti")
                        .url("http://meusite.com.br")
                );
    }
}

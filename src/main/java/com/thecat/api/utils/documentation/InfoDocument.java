package com.thecat.api.utils.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InfoDocument {
    @Bean
    public OpenAPI customApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("TheCatApi")
                        .version("1.0")
                        .description("Prueba tecnica para XpertGroup")
                )
        ;
    }
}

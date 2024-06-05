package com.example.reactive.config;

import com.example.reactive.handlers.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/** RouterConfig */
@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> router(ProductHandler productHandler) {
        return RouterFunctions.route().GET("/products", productHandler::getAll).build();
    }
}

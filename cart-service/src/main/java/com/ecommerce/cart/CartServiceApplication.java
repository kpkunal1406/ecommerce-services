package com.ecommerce.cart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.ecommerce.commons",
        "com.ecommerce.cart"
})
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8183")},
        info = @Info(title = "Cart API", version = "1.0.0", description = "API for managing cart")
)
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

}
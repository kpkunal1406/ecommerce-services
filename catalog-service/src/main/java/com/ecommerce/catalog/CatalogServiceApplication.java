package com.ecommerce.catalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.ecommerce",
        "com.ecommerce.commons"
})
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8184")},
        info = @Info(title = "Catalog API", version = "1.0.0", description = "API for managing products & categories")
)
public class CatalogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
package com.ecommerce.order;

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
        servers = {@Server(url = "http://localhost:8186")},
        info = @Info(title = "Order API", version = "1.0.0", description = "API for managing orders")
)
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

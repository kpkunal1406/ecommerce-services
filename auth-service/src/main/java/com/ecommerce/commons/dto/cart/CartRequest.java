package com.ecommerce.commons.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequest {
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    private String name;
}

package com.ecommerce.commons.dto.catalog.product;

import com.ecommerce.commons.dto.catalog.category.CategoryResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    @JsonIgnoreProperties("products")
    private CategoryResponse category;
    private List<ProductImageResponse> images;
    private Float averageStar;
    private Integer ordersCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

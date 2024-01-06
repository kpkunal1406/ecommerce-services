package com.ecommerce.cart.feign;

import com.ecommerce.commons.configuration.FeignClientConfiguration;
import com.ecommerce.commons.dto.cart.CartResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ecommerce.commons.constants.FeignConstants.ORDER_SERVICE;
import static com.ecommerce.commons.constants.PathConstants.API_V1_ORDER;

@FeignClient(name = ORDER_SERVICE, configuration = FeignClientConfiguration.class)
public interface OrderClient {
    @PostMapping(value = API_V1_ORDER)
    ResponseEntity<Object> create(@Valid @RequestBody CartResponse cart);
}

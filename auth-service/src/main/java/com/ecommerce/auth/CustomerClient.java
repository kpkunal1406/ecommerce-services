package com.ecommerce.auth;

import com.ecommerce.auth.response.CustomerResponse;
import com.ecommerce.commons.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.ecommerce.commons.constants.FeignConstants.CUSTOMER_SERVICE;
import static com.ecommerce.commons.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(name = CUSTOMER_SERVICE, configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @GetMapping(API_V1_CUSTOMER + "/username/{username}")
    ResponseEntity<CustomerResponse> getByUsername(@PathVariable("username") String username);
}

package com.ecommerce.service.impl;


import com.ecommerce.commons.dto.catalog.product.ProductResponse;
import com.ecommerce.customer.dto.request.WishlistRequest;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.entity.Wishlist;
import com.ecommerce.customer.feign.ProductClient;
import com.ecommerce.customer.repository.WishlistRepository;
import com.ecommerce.customer.service.CustomerService;
import com.ecommerce.customer.service.impl.WishlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private ProductClient productClient;

    private WishlistServiceImpl serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new WishlistServiceImpl(wishlistRepository, customerService, productClient);
    }

    @Test
    void create_shouldCreateWishlist() {
        // Given
        WishlistRequest wishlistRequest = new WishlistRequest();
        wishlistRequest.setCustomerId(1L);
        wishlistRequest.setProductId(2L);
        Customer customer = new Customer();
        customer.setId(1L);

        when(customerService.getById(1L)).thenReturn(customer);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(2L);
        ResponseEntity<ProductResponse> responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
        when(productClient.getProductById(2L)).thenReturn(responseEntity);

        Wishlist wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setCustomer(customer);
        wishlist.setProductId(2L);
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        // When
        Wishlist createdWishlist = serviceTest.create(wishlistRequest);

        // Then
        assertNotNull(createdWishlist);
        assertEquals(customer, createdWishlist.getCustomer());
        assertEquals(2L, createdWishlist.getProductId());
    }

    @Test
    void getAllByCustomerId_shouldReturnWishlistsByCustomerId() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Wishlist> expectedPage = new PageImpl<>(Collections.emptyList());
        when(wishlistRepository.findAllByCustomer_Id(1L, pageable)).thenReturn(expectedPage);

        // When
        Page<Wishlist> actualPage = serviceTest.getAllByCustomerId(1L, pageable);

        // Then
        assertNotNull(actualPage);
        assertEquals(expectedPage, actualPage);
        verify(wishlistRepository).findAllByCustomer_Id(1L, pageable);
    }

    @Test
    void delete_shouldDeleteWishlistById() {
        // Given
        Long wishlistId = 1L;
        doNothing().when(wishlistRepository).deleteById(wishlistId);

        // When
        serviceTest.delete(wishlistId);

        // Then
        verify(wishlistRepository).deleteById(wishlistId);
    }
}
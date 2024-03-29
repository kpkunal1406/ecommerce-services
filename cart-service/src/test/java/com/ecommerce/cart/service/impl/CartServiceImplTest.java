package com.ecommerce.cart.service.impl;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.feign.OrderClient;
import com.ecommerce.cart.feign.ProductClient;
import com.ecommerce.cart.repository.CartItemRepository;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.commons.mapper.BasicMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private BasicMapper basicMapper;
    @Mock
    private OrderClient orderClient;
    @Mock
    private ProductClient productClient;

    private CartServiceImpl serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new CartServiceImpl(cartRepository, cartItemRepository, basicMapper, orderClient, productClient);
    }

    @Test
    void create() {
        // Given
        Long id = 1L;
        Cart cart = new Cart();
        cart.setCustomerId(id);

        // When
        serviceTest.create(cart);

        // Then
        verify(cartRepository).save(cart);
    }

    @Test
    void addItem() {
    }

    @Test
    void removeItem() {
    }

    @Test
    void getById() {
    }

    @Test
    void completeCart() {
    }

    @Test
    void getByCustomerId() {
    }

    @Test
    void deleteByCustomerId() {
    }
}
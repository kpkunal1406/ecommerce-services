package com.ecommerce.cart.mapper;

import com.ecommerce.cart.dto.request.CartItemRequest;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.service.CartService;
import com.ecommerce.commons.dto.cart.CartRequest;
import com.ecommerce.commons.dto.cart.CartResponse;
import com.ecommerce.commons.mapper.BasicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final BasicMapper basicMapper;
    private final CartService cartService;

    public CartResponse create(CartRequest cartRequest) {
        Cart cart = basicMapper.convertTo(cartRequest, Cart.class);
        return basicMapper.convertTo(cartService.create(cart), CartResponse.class);
    }

    public CartResponse addItem(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = cartService.addItem(cartId, basicMapper.convertTo(cartItemRequest, CartItem.class));
        return basicMapper.convertTo(cart, CartResponse.class);
    }

    public CartResponse removeItem(Long cartId, Long itemId) {
        return basicMapper.convertTo(cartService.removeItem(cartId, itemId), CartResponse.class);
    }

    public CartResponse getById(Long id) {
        return basicMapper.convertTo(cartService.getById(id), CartResponse.class);
    }

    public CartResponse completeCart(Long id) {
        return basicMapper.convertTo(cartService.completeCart(id), CartResponse.class);
    }

    public CartResponse getByCustomerId(Long id) {
        return basicMapper.convertTo(cartService.getByCustomerId(id), CartResponse.class);
    }

    public void deleteByCustomerId(Long id) {
        cartService.deleteByCustomerId(id);
    }
}

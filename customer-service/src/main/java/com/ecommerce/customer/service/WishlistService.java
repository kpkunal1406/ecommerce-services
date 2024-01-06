package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.request.WishlistRequest;
import com.ecommerce.customer.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishlistService {
    Wishlist create(WishlistRequest wishlist);

    Page<Wishlist> getAllByCustomerId(Long id, Pageable pageable);

    void delete(Long id);
}

package com.ecommerce.customer.service.impl;

import com.ecommerce.customer.dto.request.WishlistRequest;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.entity.Wishlist;
import com.ecommerce.customer.feign.ProductClient;
import com.ecommerce.customer.repository.WishlistRepository;
import com.ecommerce.customer.service.CustomerService;
import com.ecommerce.customer.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final CustomerService customerService;
    private final ProductClient productClient;

    @Override
    public Wishlist create(WishlistRequest wishlistRequest) {
        Wishlist wishlist = new Wishlist();
        Customer customer = customerService.getById(wishlistRequest.getCustomerId());
        Long productId = productClient.getProductById(wishlistRequest.getProductId()).getBody().getId();
        wishlist.setCustomer(customer);
        wishlist.setProductId(productId);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Page<Wishlist> getAllByCustomerId(Long id, Pageable pageable) {
        return wishlistRepository.findAllByCustomer_Id(id, pageable);
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}
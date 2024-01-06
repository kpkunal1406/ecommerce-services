package com.ecommerce.customer.mapper;

import com.ecommerce.commons.mapper.BasicMapper;
import com.ecommerce.customer.dto.request.WishlistRequest;
import com.ecommerce.customer.dto.response.WishlistResponse;
import com.ecommerce.customer.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistMapper {

    private final BasicMapper basicMapper;
    private final WishlistService wishlistService;

    public WishlistResponse create(WishlistRequest wishlistRequest) {
        return basicMapper.convertTo(wishlistService.create(wishlistRequest), WishlistResponse.class);
    }

    public Page<WishlistResponse> getAllByCustomerId(Long customerId, Pageable pageable) {
        return wishlistService.getAllByCustomerId(customerId, pageable).map(w -> basicMapper.convertTo(w, WishlistResponse.class));
    }

    public void deleteById(Long id) {
        wishlistService.delete(id);
    }
}

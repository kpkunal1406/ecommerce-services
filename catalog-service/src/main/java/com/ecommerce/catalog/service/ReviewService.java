package com.ecommerce.catalog.service;

import com.ecommerce.catalog.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review create(Long productId, Review review);

    Review update(Long reviewId, Long productId, Review review);

    Review getById(Long reviewId);

    Page<Review> getByProductId(Long productId, Pageable pageable);

    Page<Review> getByCustomerId(Long customerId, Pageable pageable);

    void deleteById(Long reviewId);
}

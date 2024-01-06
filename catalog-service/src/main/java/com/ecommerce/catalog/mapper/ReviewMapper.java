package com.ecommerce.catalog.mapper;

import com.ecommerce.catalog.dto.request.ReviewRequest;
import com.ecommerce.catalog.dto.response.ReviewResponse;
import com.ecommerce.catalog.entity.Review;
import com.ecommerce.catalog.service.ReviewService;
import com.ecommerce.commons.mapper.BasicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final BasicMapper basicMapper;
    private final ReviewService reviewService;

    public ReviewResponse create(ReviewRequest review) {
        Review r = basicMapper.convertTo(review, Review.class);
        return basicMapper.convertTo(reviewService.create(review.getProductId(), r), ReviewResponse.class);
    }

    public ReviewResponse update(Long reviewId, ReviewRequest review) {
        Review r = basicMapper.convertTo(review, Review.class);
        return basicMapper.convertTo(reviewService.update(reviewId, review.getProductId(), r), ReviewResponse.class);
    }

    public ReviewResponse getById(Long reviewId) {
        return basicMapper.convertTo(reviewService.getById(reviewId), ReviewResponse.class);
    }

    public Page<ReviewResponse> getByProductId(Long productId, Pageable pageable) {
        return reviewService.getByProductId(productId, pageable).map(review -> basicMapper.convertTo(review, ReviewResponse.class));
    }

    public Page<ReviewResponse> getByCustomerId(Long customerId, Pageable pageable) {
        return reviewService.getByCustomerId(customerId, pageable).map(review -> basicMapper.convertTo(review, ReviewResponse.class));
    }

    public void deleteById(Long reviewId) {
        reviewService.deleteById(reviewId);
    }
}

package com.ecommerce.catalog.service.impl;

import com.ecommerce.catalog.entity.Product;
import com.ecommerce.catalog.entity.Review;
import com.ecommerce.catalog.feign.CustomerClient;
import com.ecommerce.catalog.repository.ReviewRepository;
import com.ecommerce.catalog.service.ProductService;
import com.ecommerce.catalog.service.ReviewService;
import com.ecommerce.commons.exceptions.NotFoundException;
import com.ecommerce.commons.utils.UpdatingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerClient customerClient;
    private final ProductService productService;

    @Override
    public Review create(Long productId, Review review) {
        customerClient.getById(review.getCustomerId()); // If customer doesn't exist feign will throw an exception
        Product product = productService.getById(productId);
        List<Review> reviews = product.getReviews();
        reviews.add(review);

        int sum = reviews.stream().mapToInt(Review::getRating).sum();
        float average = (float) sum / (float) reviews.size();

        product.setAverageStar(average);
        productService.update(product);
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Long reviewId, Long productId, Review review) {
        Review oldReview = getById(reviewId);
        BeanUtils.copyProperties(review, oldReview, UpdatingUtil.getNullPropertyNames(review));
        return create(productId, oldReview);
    }

    @Override
    public Review getById(Long reviewId) {
        return reviewRepository
                .findById(reviewId).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    @Override
    public Page<Review> getByProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByProduct_Id(productId, pageable);
    }

    @Override
    public Page<Review> getByCustomerId(Long customerId, Pageable pageable) {
        return reviewRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

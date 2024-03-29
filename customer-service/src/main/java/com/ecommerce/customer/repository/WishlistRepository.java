package com.ecommerce.customer.repository;

import com.ecommerce.customer.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByCustomer_Id(Long id, Pageable pageable);
}

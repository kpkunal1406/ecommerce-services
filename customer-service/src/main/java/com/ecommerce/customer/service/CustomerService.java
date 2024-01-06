package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    Customer registration(Customer customer);

    Customer uploadImage(MultipartFile image, Long customerId);

    Page<Customer> getAll(Pageable pageable);

    Customer getById(Long id);

    Customer getByUsername(String username);

    void deleteById(Long id);
}

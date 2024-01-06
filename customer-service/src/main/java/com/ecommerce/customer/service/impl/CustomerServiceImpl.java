package com.ecommerce.customer.service.impl;

import com.ecommerce.commons.dto.authentication.AuthenticationRequest;
import com.ecommerce.commons.dto.cart.CartRequest;
import com.ecommerce.commons.exceptions.AlreadyExistException;
import com.ecommerce.commons.exceptions.NotFoundException;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.feign.AuthClient;
import com.ecommerce.customer.feign.CartClient;
import com.ecommerce.customer.feign.ImageClient;
import com.ecommerce.customer.repository.CustomerRepository;
import com.ecommerce.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ImageClient imageClient;
    private final AuthClient authClient;
    private final CartClient cartClient;

    @Override
    public Customer registration(Customer customer) {
        String password = customer.getPassword();
        customer.setPassword(new BCryptPasswordEncoder().encode(password));
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            throw new AlreadyExistException("Username is already taken.");
        }
        Customer c = customerRepository.save(customer);
        authClient.authenticate(new AuthenticationRequest(customer.getUsername(), password));
        cartClient.createCart(new CartRequest(c.getId(), ""));
        return c;
    }

    @Override
    public Customer uploadImage(MultipartFile image, Long customerId) {
        Customer customer = getById(customerId);
        if (image != null && !image.isEmpty()) {
            String imageUrl = imageClient.uploadImage(image);
            customer.setImageUrl(imageUrl);
        }
        return customer;
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}

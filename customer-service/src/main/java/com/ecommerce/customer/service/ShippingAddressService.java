package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.ShippingAddress;

import java.util.List;

public interface ShippingAddressService {
    ShippingAddress createShippingAddress(Long customerId, ShippingAddress address);

    ShippingAddress getShippingAddressById(Long id);

    List<ShippingAddress> getAllShippingAddressesByCustomerId(Long customerId);

    ShippingAddress updateShippingAddress(Long id, ShippingAddress addressDetails);

    void deleteShippingAddress(Long id);
}

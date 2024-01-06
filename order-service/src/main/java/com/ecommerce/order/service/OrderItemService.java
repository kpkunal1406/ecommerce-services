package com.ecommerce.order.service;

import com.ecommerce.order.entity.OrderItem;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);

    OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem);

    void deleteOrderItem(Long id);
}
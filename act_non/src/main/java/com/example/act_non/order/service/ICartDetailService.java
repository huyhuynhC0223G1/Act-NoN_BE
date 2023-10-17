package com.example.act_non.order.service;

import com.example.act_non.customer.model.Customer;

public interface ICartDetailService {
    Long addQuantityCart(Long productId, Long quantity, Customer customer);
    Double updateQuantity(Long productId, Long quantity, Customer customer);
}

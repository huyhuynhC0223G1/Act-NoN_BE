package com.example.act_non.order.service;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.order.model.CartDetail;

import java.util.List;

public interface ICartDetailService {
    List<CartDetail> cartDetailList(Long customerId);
    Long addQuantityCart(Long productId, Long quantity, Customer customer);
    Double updateQuantity(Long productId, Long quantity, Customer customer);
}

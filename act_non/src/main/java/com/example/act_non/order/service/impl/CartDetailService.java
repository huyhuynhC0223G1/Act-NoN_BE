package com.example.act_non.order.service.impl;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.order.model.CartDetail;
import com.example.act_non.order.repository.ICartDetailRepository;
import com.example.act_non.order.service.ICartDetailService;
import com.example.act_non.product.model.Product;
import com.example.act_non.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ICartDetailRepository cartDetailRepository;
    @Autowired
    private IProductRepository productRepository;

    public List<CartDetail> cartDetailList(Customer customer) {
        return cartDetailRepository.findByCustomer(customer);
    }

    @Override
    public Long addQuantityCart(Long productId, Long quantity, Customer customer) {
        Long addedQuantity = quantity;
        Product product = productRepository.findById(productId).get();
        CartDetail cartDetail = cartDetailRepository.findByCustomerAndProduct(customer, product);
        if (cartDetail != null) {
            addedQuantity = cartDetail.getQuantity() + quantity;
            cartDetail.setQuantity(addedQuantity);
        } else {
            cartDetail = new CartDetail();
            cartDetail.setQuantity(quantity);
            cartDetail.setCustomer(customer);
            cartDetail.getProduct(product);
        }
        cartDetailRepository.save(cartDetail);
        return addedQuantity;
    }

    public Double updateQuantity(Long productId, Long quantity, Customer customer){
        cartDetailRepository.updateQuantity(quantity, productId, customer.getId());
        Product product =  productRepository.findById(productId).get();
        Double subTotal = product.getPrice() * quantity;
        return subTotal;
    }
}

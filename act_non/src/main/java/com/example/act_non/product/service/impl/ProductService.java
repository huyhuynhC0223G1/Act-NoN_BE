package com.example.act_non.product.service.impl;

import com.example.act_non.product.model.Product;
import com.example.act_non.product.repository.IProductRepository;
import com.example.act_non.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<Product> findAllProduct(Pageable pageable, String name, String price, String typeName, String description) {
        return productRepository.findAllProduct(pageable, name, price, typeName, description);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }
}

package com.example.act_non.product.service;

import com.example.act_non.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
Page<Product> findAllProduct(Pageable pageable, String name, String price, String typeName, String description);

Product getProductById(Integer id);
}

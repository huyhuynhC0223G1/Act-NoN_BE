package com.example.act_non.product.repository;

import com.example.act_non.product.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {
}

package com.example.act_non.order.model;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.product.model.Product;

import javax.persistence.*;

@Entity
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    private Long quantity;
    private Boolean flagDeleted;

    public CartDetail() {
    }

    public CartDetail(Long id, Customer customer, Product product, Long quantity, Boolean flagDeleted) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.flagDeleted = flagDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct(Product product) {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }


}

package com.example.act_non.order.model;

import com.example.act_non.product.model.Product;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double currentPrice;
    private Long quantity;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Double currentPrice, Long quantity, Orders orders, Product product) {
        this.id = id;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.orders = orders;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

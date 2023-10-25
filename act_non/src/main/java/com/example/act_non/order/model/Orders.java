package com.example.act_non.order.model;

import com.example.act_non.customer.model.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    private String note;
    private Double totalPrice;
    private Boolean flagDeleted;

    @ManyToOne
    private Customer customer;

    public Orders() {
    }

    public Orders(Long id, LocalDateTime dateCreated, String note, Double totalPrice, Boolean flagDeleted, Customer customer) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.note = note;
        this.totalPrice = totalPrice;
        this.flagDeleted = flagDeleted;
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

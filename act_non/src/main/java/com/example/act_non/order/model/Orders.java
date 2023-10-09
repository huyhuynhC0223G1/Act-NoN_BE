package com.example.act_non.order.model;

import com.example.act_non.customer.model.Customer;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateCreated;
    private String note;
    private Boolean flagDeleted;
    @ManyToOne
    private Customer customer;

    public Orders() {
    }


    public Orders(Long id, String dateCreated, String note, Boolean flagDeleted, Customer customer) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.note = note;
        this.flagDeleted = flagDeleted;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
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

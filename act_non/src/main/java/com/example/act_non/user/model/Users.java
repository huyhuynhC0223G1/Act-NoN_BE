package com.example.act_non.user.model;

import com.example.act_non.customer.model.Customer;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean flagDelete;
    @OneToOne
    private Customer customer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns = {@JoinColumn(name = "users_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles;
    public Users() {
    }

    public Users(Long id, String username, String password, Boolean flagDelete, Customer customer, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flagDelete = flagDelete;
        this.customer = customer;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Boolean getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

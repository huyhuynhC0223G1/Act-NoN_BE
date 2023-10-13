package com.example.act_non.product.model;

import javax.persistence.*;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean flagDetele;

    public ProductType() {
    }

    public ProductType(Long id, String name, Boolean flagDetele) {
        this.id = id;
        this.name = name;
        this.flagDetele = flagDetele;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlagDetele() {
        return flagDetele;
    }

    public void setFlagDetele(Boolean flagDetele) {
        this.flagDetele = flagDetele;
    }
}

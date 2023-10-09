package com.example.act_non.product.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String img;

    @ManyToOne
    private ProductType productType;
    private Boolean flag_delete;
    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String img, ProductType productType, Boolean flag_delete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.productType = productType;
        this.flag_delete = flag_delete;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Boolean getFlag_delete() {
        return flag_delete;
    }

    public void setFlag_delete(Boolean flag_delete) {
        this.flag_delete = flag_delete;
    }
}

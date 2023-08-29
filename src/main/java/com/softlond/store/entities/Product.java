package com.softlond.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties("products")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private Set<SaleProduct> sales;

//    @ManyToMany
//    @JoinColumn(name = "sale_id")
//    @JsonIgnoreProperties("products")
//    private List<Sale> sales;

//    @ManyToMany(mappedBy = "products")
//    @JsonIgnoreProperties("products")
//    public Set<Sale> sales = new HashSet<>();



    public Product() {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public Set<SaleProduct> getSaleProducts() {
//        return saleProducts;
//    }
//
//    public void setSaleProducts(Set<SaleProduct> saleProducts) {
//        this.saleProducts = saleProducts;
//    }

    public Set<SaleProduct> getSales() {
        return sales;
    }

    public void setSales(Set<SaleProduct> sales) {
        this.sales = sales;
    }

}

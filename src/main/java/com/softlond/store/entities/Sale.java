package com.softlond.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("sales")
    private Client client;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sales")
//    @JsonIgnoreProperties("sale")
//    private List<Product> products;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sale_product", joinColumns = @JoinColumn(name = "sale_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties("sales")
    private Set<Product> products = new HashSet<>();

    private Long totalSale;



    public Sale(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Long getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Long totalSale) {
        this.totalSale = totalSale;
    }
}

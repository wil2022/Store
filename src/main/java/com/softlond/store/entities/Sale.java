package com.softlond.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"sales"})
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sale")
    @JsonIgnoreProperties("sale")
    private Set<SaleProduct> products;

    private Double subtotal;
    private Double discount;
    private Double totalSale;


}

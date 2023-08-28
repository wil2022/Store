package com.softlond.store.repositories.contracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softlond.store.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRespository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.* FROM product p " +
            " WHERE price > :price", nativeQuery = true)
    public List<Product> productsWithHigherPrice(@Param("price") Double price);

    public Product findByNameIgnoreCase(@Param("name") String name);
}

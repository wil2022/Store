package com.softlond.store.repositories.contracts;

import com.softlond.store.entities.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleProductRepository extends JpaRepository<SaleProduct, Long> {
}

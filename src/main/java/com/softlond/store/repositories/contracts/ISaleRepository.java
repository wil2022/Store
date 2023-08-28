package com.softlond.store.repositories.contracts;

import com.softlond.store.entities.Client;
import com.softlond.store.entities.Product;
import com.softlond.store.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByDate(@Param("date") LocalDate date);

    List<Sale> findByClientId(Long client_id);

}

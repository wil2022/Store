package com.softlond.store.services.contracts;

import com.softlond.store.entities.Client;
import com.softlond.store.entities.Sale;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {

    ResponseEntity<List<Sale>> findAll();
    ResponseEntity<Sale> create(Sale sale);
   // ResponseEntity<Sale> update(Sale sale);
   // ResponseEntity<Boolean> delete(Long id);

    ResponseEntity<Boolean> addProductToSale(Long sale_id, Long product_id, int units);

    ResponseEntity<Boolean> closeSale(Long sale_id);

    ResponseEntity<List<Sale>> findByDate(LocalDate date);

    ResponseEntity<List<Sale>> findByClientId(Long client_id);

    ResponseEntity<List<Sale>> findByClientAndRangeDate(Long client_id, LocalDate startDate, LocalDate endDate);


}

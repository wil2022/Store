package com.softlond.store.repositories.contracts;

import com.softlond.store.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByDate(@Param("date") LocalDate date);

    List<Sale> findByClientId(Long client_id);

    @Query(value = "SELECT * FROM sale " +
            " WHERE client_id = :clientId AND date BETWEEN :startDate AND :endDate", nativeQuery = true)

    List<Sale> findByClientAndRangeDate(@Param("clientId") Long clientId, @Param("startDate") LocalDate StartDate,
                                        @Param("endDate") LocalDate endDate);

}

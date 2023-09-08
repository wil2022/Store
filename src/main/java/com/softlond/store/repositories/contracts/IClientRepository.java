package com.softlond.store.repositories.contracts;

import com.softlond.store.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);

    @Query("select u from Client u where u.username = ?1")
    Optional<Client> getName(String username);
}

package com.softlond.store.services.contracts;

import com.softlond.store.entities.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClientService {

    ResponseEntity<List<Client>> findAll();
    ResponseEntity<Client> create(Client client);
    ResponseEntity<Client> update(Client client);
    ResponseEntity<Boolean> delete(Long id);



}

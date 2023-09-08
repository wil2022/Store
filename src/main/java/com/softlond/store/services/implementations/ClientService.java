package com.softlond.store.services.implementations;

import com.softlond.store.entities.Client;
import com.softlond.store.repositories.contracts.IClientRepository;
import com.softlond.store.services.contracts.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<List<Client>> findAll() {
        try{
            List<Client> userEntities = this.clientRepository.findAll();
            return new ResponseEntity<>(userEntities, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Client> create(Client client) {
        try{
            Client clientSaved = this.clientRepository.save(client);
            return new ResponseEntity<>(clientSaved, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Client> update(Client client) {
        try{
            Client clientUpdated = this.clientRepository.save(client);
            return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        try{
            this.clientRepository.deleteById(id);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

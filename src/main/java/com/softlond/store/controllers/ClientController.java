package com.softlond.store.controllers;

import com.softlond.store.entities.Client;
import com.softlond.store.services.contracts.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<List<Client>> getAllClients(){

        return this.clientService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return this.clientService.update(client);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteClient(@RequestParam Long id){
        return this.clientService.delete(id);
    }


}

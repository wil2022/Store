package com.softlond.store.controllers;

import com.softlond.store.controllers.request.CreateClientDTO;
import com.softlond.store.entities.Client;
import com.softlond.store.entities.ERole;
import com.softlond.store.entities.RoleEntity;
import com.softlond.store.repositories.contracts.IClientRepository;
import com.softlond.store.services.contracts.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Client>> getAllClients(){

        return this.clientService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@Valid @RequestBody CreateClientDTO createClientDTO){


            Set<RoleEntity> roles = createClientDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Client client = Client.builder()
                .name(createClientDTO.getName())
                .document(createClientDTO.getDocument())
                .username(createClientDTO.getUsername())
                .password(passwordEncoder.encode(createClientDTO.getPassword()))
                .email(createClientDTO.getEmail())
                .roles(roles)
                .build();

        clientRepository.save(client);

        return ResponseEntity.ok(client);


        //return this.clientService.create(client);
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

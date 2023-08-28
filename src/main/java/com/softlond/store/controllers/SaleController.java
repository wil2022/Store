package com.softlond.store.controllers;


import com.softlond.store.entities.Sale;
import com.softlond.store.services.contracts.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping("/list")
    public ResponseEntity<List<Sale>> getAllSales(){

        return this.saleService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale){

        return this.saleService.create(sale);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Boolean> addProductToSale(@RequestParam Long saleId, @RequestParam Long productId) {

       return this.saleService.addProductToSale(saleId,productId);
    }


}
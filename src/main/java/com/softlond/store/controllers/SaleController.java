package com.softlond.store.controllers;


import com.softlond.store.entities.Sale;
import com.softlond.store.services.contracts.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/list-by-date")
    public ResponseEntity<List<Sale>> getSalesByDate(@RequestParam LocalDate date){
        return this.saleService.findByDate(date);
    }

    @GetMapping("/list-by-client")
    public ResponseEntity<List<Sale>> getSalesByClient(@RequestParam Long clientId){
        return this.saleService.findByClientId(clientId);
    }

    @GetMapping("/list-by-client-and-range-dates")
    public ResponseEntity<List<Sale>> getSalesByClientAndDates(@RequestParam Long clientId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return this.saleService.findByClientAndRangeDate(clientId,startDate,endDate);
    }


    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale){

        return this.saleService.create(sale);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Boolean> addProductToSale(@RequestParam Long saleId, @RequestParam Long productId, @RequestParam int units) {

       return this.saleService.addProductToSale(saleId,productId,units);
    }

    @PostMapping("/close-sale")
    public ResponseEntity<Boolean> closeSale(@RequestParam Long saleId){
        return this.saleService.closeSale(saleId);
    }


}

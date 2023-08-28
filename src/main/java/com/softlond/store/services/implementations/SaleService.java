package com.softlond.store.services.implementations;

import com.softlond.store.entities.Product;
import com.softlond.store.entities.Sale;
import com.softlond.store.repositories.contracts.IProductRespository;
import com.softlond.store.repositories.contracts.ISaleRepository;
import com.softlond.store.services.contracts.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IProductRespository productRespository;

    @Override
    public ResponseEntity<List<Sale>> findAll() {
        try {
            List<Sale> sales = this.saleRepository.findAll();
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Sale> create(Sale sale) {
        try {

            Sale saleSaved = this.saleRepository.save(sale);
            return new ResponseEntity<>(saleSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> addProductToSale(Long sale_id, Long product_id) {
        try {
            Sale sale = this.saleRepository.findById(sale_id).orElse(null);
            Product product = this.productRespository.findById(product_id).orElse(null);

            if (sale != null && product != null) {
                sale.getProducts().add(product);
                product.getSales().add(sale);
                saleRepository.save(sale);
            }
//             double sum = 0;
//            for (Product p:sale.getProducts()) {
//                sum += p.getPrice();
//
//            }
//            sale.setTotalSale(sum);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> closeSale(Long sale_id) {
        return null;
    }
}

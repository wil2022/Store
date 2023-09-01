package com.softlond.store.services.implementations;

import com.softlond.store.entities.Product;
import com.softlond.store.entities.Sale;
import com.softlond.store.entities.SaleProduct;
import com.softlond.store.repositories.contracts.IProductRespository;
import com.softlond.store.repositories.contracts.ISaleProductRepository;
import com.softlond.store.repositories.contracts.ISaleRepository;
import com.softlond.store.services.contracts.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IProductRespository productRespository;

    @Autowired
    private ISaleProductRepository saleProductRepository;

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
    public ResponseEntity<Boolean> addProductToSale(Long sale_id, Long product_id, int units) {

            Sale sale = this.saleRepository.findById(sale_id).orElse(null);
            Product product = this.productRespository.findById(product_id).orElse(null);
            SaleProduct product1 = new SaleProduct();

                if (sale != null && product != null && sale.getTotalSale() == null) {

                    product1.setSale(sale);
                    product1.setProduct(product);
                    product1.setUnits(units);
                    product1.setPriceTotalProduct(product.getPrice() * units);

                    sale.getProducts().add(product1);
                    product.getSales().add(product1);

                    saleProductRepository.save(product1);

                    return new ResponseEntity<>(true,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }

    @Override
    public ResponseEntity<Boolean> closeSale(Long sale_id) {

            Sale sale = this.saleRepository.findById(sale_id).orElse(null);

            double sum = 0;
            double discount = 0;
            double discount2;

            if(sale != null) {
                for (SaleProduct product : sale.getProducts()) {
                    sum += product.getPriceTotalProduct();
                }

                LocalDate newDate = sale.getDate().minusDays(31);

                for (Sale sales : sale.getClient().getSales()) {

                    if (sales.getTotalSale() != null && sales.getTotalSale() > 1000000
                            && (sales.getDate().isEqual(newDate) || sales.getDate().isAfter(newDate))) {
                        discount = sum * (sale.getDiscount() / 100);
                        break;
                    }

                }

                discount2 = gameOptions(sum, sale.getDiscount());

                sale.setDiscount(discount + discount2);
                sale.setSubtotal(sum);
                sale.setTotalSale(sum - discount - discount2);
                saleRepository.save(sale);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public double gameOptions(double totalSale, double disc){

        Random random = new Random();
        int randomNumber;
        int count = 0;
        double discount = 0;

        do {
            randomNumber = random.nextInt(3) + 1;
            switch (randomNumber) {
                case 1 -> {
                    discount = totalSale * (disc / 100);
                    System.out.println("ganaste");}
                case 2 -> System.out.println("nuevo intento");
                case 3 -> System.out.println("no ganaste");
            }
            count++;
        } while (randomNumber == 2 && count < 3);

        return discount;
    }

    @Override
    public ResponseEntity<List<Sale>> findByDate(LocalDate date) {
        try{
            List<Sale> salesByDate = this.saleRepository.findByDate(date);
            return new ResponseEntity<>(salesByDate, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Sale>> findByClientId(Long client_id) {
        try{
            List<Sale> salesByClient = this.saleRepository.findByClientId(client_id);
            return new ResponseEntity<>(salesByClient, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Sale>> findByClientAndRangeDate(Long client_id, LocalDate startDate, LocalDate endDate) {
        try{
            List<Sale> salesByClientAndDates = this.saleRepository.findByClientAndRangeDate(client_id, startDate, endDate);
            return new ResponseEntity<>(salesByClientAndDates, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.sahil.transactional.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.sahil.transactional.entities.Product;
import com.sahil.transactional.repo.ProductRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;

/**
 * ProductService
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    // can use @Transactional(rollBackOn) to add Exception for which it should be
    // rolled back
    // by default it is for RuntimeException
    // Exception should be thrown outside method to make use of @Transactional ,
    // meaning using try-catch
    // will not work for rollback

    // Propagation
    // REQUIRED -> default , if transaction not there it create otherwise use already transaction context
    // REQUIRES_NEW -> Always create a new transaction not matter if it exsists or not
    // MANDATORY -> it will throw Exception if no transaction is there while calling method otherwise 
    // use the already exsisting transaction context
    // NEVER -> opposite of MANDATORY
    // SUPPORTS -> if transaction exsists then no issue , same if no transaction it run 
    // NOT_SUPPORTED -> only execute without transaction, even if there is transaction exsists
    @Transactional(value = TxType.REQUIRES_NEW)
    public void addProduct(Product product, String name) {
        product.setName(name);
        productRepo.save(product);
        // try {
        // throw new RuntimeException();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    // Isolation 
    // Problems 
    //   - dirty reads
    //   - repetable reads
    //   - phantom reads
    // four option
    // - READ_COMMITTED -> read value after committed 
    // - READ_UNCOMMITTED -> read value even when uncommitted 
    // - REPETABLE_READ 
    // - SERIALIZABLE -> highest level of isolation
    @Transactional
    public void addTenProduct() {
        try {
            for (int i = 0; i < 10; i++) {
                Product p = new Product();
                addProduct(p, "Product" + i);
                if (i == 5)
                    throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

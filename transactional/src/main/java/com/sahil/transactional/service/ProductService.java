package com.sahil.transactional.service;

import org.springframework.stereotype.Service;

import com.sahil.transactional.entities.Product;
import com.sahil.transactional.repo.ProductRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * ProductService
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    @Transactional
    // can use @Transactional(rollBackOn) to add Exception for which it should be
    // rolled back
    // by default it is for RuntimeException
    // Exception should be thrown outside method to make use of @Transactional ,
    // meaning using try-catch
    // will not work for rollback
    public void addProduct(Product product, String name) {
        product.setName(name);
        productRepo.save(product);
        throw new RuntimeException(":(");
        // try {
        // throw new RuntimeException();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }
}

package com.sahil.transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sahil.transactional.entities.Product;
import com.sahil.transactional.service.ProductService;

import lombok.RequiredArgsConstructor;

/**
 * DataLoader
 */
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService service;

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product();
        service.addProduct(p1, "tomato");
    }

}

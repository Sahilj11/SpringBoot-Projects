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
   public void addProduct(Product product,String name){
       product.setName(name);
       productRepo.save(product);
       throw new RuntimeException(":(");
   }
}

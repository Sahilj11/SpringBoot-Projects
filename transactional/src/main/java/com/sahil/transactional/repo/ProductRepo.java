package com.sahil.transactional.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.transactional.entities.Product;

/**
 * ProductRepo
 */
@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

    
}

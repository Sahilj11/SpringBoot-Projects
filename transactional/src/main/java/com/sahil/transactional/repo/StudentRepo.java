package com.sahil.transactional.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahil.transactional.entities.Student;

/**
 * StudentRepo
 */
public interface StudentRepo extends JpaRepository<Student,Integer>{
    
}

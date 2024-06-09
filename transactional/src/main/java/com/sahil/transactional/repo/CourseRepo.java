package com.sahil.transactional.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahil.transactional.entities.Course;

/**
 * CourseRepo
 */
public interface CourseRepo extends JpaRepository<Course,Integer>{
    
}

package com.sahil.fileupload.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.fileupload.entities.UserEntity;

/**
 * UserRepo
 */
public interface UserRepo extends JpaRepository<UserEntity,Long>{

   Optional<UserEntity> findByUsername(String username); 
}

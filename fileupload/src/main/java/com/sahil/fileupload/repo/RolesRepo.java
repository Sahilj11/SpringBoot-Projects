package com.sahil.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahil.fileupload.entities.Roles;

/**
 * RolesRepo
 */
public interface RolesRepo extends JpaRepository<Roles,Integer>{

    Roles findByRole(String role);
}

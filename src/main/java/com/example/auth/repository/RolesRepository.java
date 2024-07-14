package com.example.auth.repository;

import com.example.auth.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    List<Roles> findByNameIn(List<String> name);

}

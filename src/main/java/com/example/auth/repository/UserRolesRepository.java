package com.example.auth.repository;

import com.example.auth.entity.User;
import com.example.auth.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UsersRoles, Integer> {

    List<UsersRoles> findByUser(User user);

}

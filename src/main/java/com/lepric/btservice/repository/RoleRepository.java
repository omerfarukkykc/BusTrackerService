package com.lepric.btservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

package com.lepric.btservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByRoleName(String roleName);
    public boolean existsRoleByRoleName(String roleName);

}

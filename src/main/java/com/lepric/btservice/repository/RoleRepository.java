package com.lepric.btservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);
    public boolean existsRoleByRoleName(String roleName);

}

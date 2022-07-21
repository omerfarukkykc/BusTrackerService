package com.lepric.btservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lepric.btservice.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    public Optional<Privilege> findByPrivilegeName(String PrivilegeName);
    public boolean existsPrivilageByPrivilegeName(String PrivilegeName);
}

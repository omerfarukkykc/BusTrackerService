package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Fee;

public interface FeeRepository  extends JpaRepository<Fee, Long> {
    
}

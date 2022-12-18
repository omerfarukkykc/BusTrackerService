package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Dealer;

public interface DealerRepository extends JpaRepository<Dealer,Long>{
    
}

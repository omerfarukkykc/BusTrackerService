package com.lepric.btservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Bus;


public interface BusRepository extends JpaRepository<Bus, Long> {

}

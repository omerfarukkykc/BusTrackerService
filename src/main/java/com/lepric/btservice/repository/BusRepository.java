package com.lepric.btservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Bus;


public interface BusRepository extends JpaRepository<Bus, Long> {

}

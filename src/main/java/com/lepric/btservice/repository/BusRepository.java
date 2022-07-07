package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Busses;

public interface BusRepository extends JpaRepository<Busses, Long> {


}

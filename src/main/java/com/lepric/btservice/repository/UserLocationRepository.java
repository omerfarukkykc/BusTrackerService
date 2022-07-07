package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Location;

public interface UserLocationRepository extends JpaRepository<Location, Long> {


}

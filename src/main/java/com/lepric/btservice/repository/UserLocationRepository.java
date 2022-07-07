package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {


}

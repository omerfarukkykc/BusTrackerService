package com.lepric.btservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
    
}

package com.lepric.btservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
    //List<Route> findByDistrictID(long districtID);
}

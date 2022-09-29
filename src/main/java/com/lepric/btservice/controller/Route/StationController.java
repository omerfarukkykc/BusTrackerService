package com.lepric.btservice.controller.Route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lepric.btservice.model.Station;
import com.lepric.btservice.service.RouteService;

public class StationController {
    /*
     * Get Stations routes
     * Add Station
     * Delete Station
     * Update Station
     * Get Station occupancy
     */
    @Autowired
    RouteService routeService;

   
    
}

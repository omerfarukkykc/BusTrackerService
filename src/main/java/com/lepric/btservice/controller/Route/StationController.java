package com.lepric.btservice.controller.Route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.repository.CityRepository;
import com.lepric.btservice.service.CityService;
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

    @GetMapping("station/{stationID}")
    public ResponseEntity<Station> GetRoutes(@PathVariable("stationID") long stationID){
        return new ResponseEntity<Station>(routeService.GetStation(stationID),HttpStatus.OK);
    }
    @GetMapping("district/{districtID}/station")
    public ResponseEntity<List<Station>> GetStations(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Station>>(routeService.GetStations(districtID),HttpStatus.OK);
    }
}

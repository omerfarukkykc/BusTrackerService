package com.lepric.btservice.controller.Route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.model.Route;
import com.lepric.btservice.repository.RouteRepository;
import com.lepric.btservice.service.CityService;
import com.lepric.btservice.service.RouteService;

@RestController
@RequestMapping("/")
public class RouteController {
    /*
     * Get Route Stations
     * Get Roue Real Time Data
     * Get Rote Dates
     * Get All Routes
     * Add Route
     * Delete Route
     * Update Route
     * Get Route
     */
    @Autowired(required=true)
    private CityService cityService;

    @Autowired
    private RouteService routeService;

    @GetMapping("route/{routeID}")
    public ResponseEntity<Route> getRoute(@PathVariable("routeID") long routeID){
        return new ResponseEntity<Route>(routeService.GetRoute(routeID),HttpStatus.OK);
        //REq düzenlenecek
    }
    @GetMapping("district/{districtID}/route")
    public ResponseEntity<List<Route>> GetRoutes(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Route>>(routeService.GetRoutes(districtID),HttpStatus.OK);
    }
    
}

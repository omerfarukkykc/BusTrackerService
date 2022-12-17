package com.lepric.btservice.controller.Route;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Fee;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.RouteTime;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.payload.response.BusResponse;
import com.lepric.btservice.payload.response.RouteResponse;
import com.lepric.btservice.payload.response.StationResponse;
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
    public ResponseEntity<RouteResponse> GetRoute(@PathVariable("routeID") long routeID){
        return new ResponseEntity<RouteResponse>(routeService.GetRouteResponse(routeID),HttpStatus.OK);
    }
    @GetMapping("route/{routeID}/times")
    public ResponseEntity<List<RouteTime>> GetRouteTimes(@PathVariable("routeID") long routeID){
        return new ResponseEntity<List<RouteTime>>(routeService.GetRouteTimes(routeID),HttpStatus.OK);
    }
    

    @GetMapping("route/realTimeData/{routeID}")
    public ResponseEntity<List<BusResponse>> getRealTimeData(@PathVariable("routeID") long routeID){
        List<Bus> dbresponse = routeService.GetRouteRealTimeData(routeID);
        List<BusResponse> response = new ArrayList<BusResponse>(); 
        dbresponse.forEach((item) ->{
            response.add(new BusResponse(item));
        });
        return new ResponseEntity<List<BusResponse>>(response,HttpStatus.OK);      
    }
    @GetMapping("route/{routeID}/fee")
    public ResponseEntity<Fee> GetRouteFee(@PathVariable("routeID") long routeID){
        Route route = routeService.GetRoute(routeID);
        return new ResponseEntity<Fee>(route.getFee(),HttpStatus.OK);
    }

}

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
import com.lepric.btservice.model.Route;
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
        return new ResponseEntity<RouteResponse>(routeService.GetRoute(routeID),HttpStatus.OK);
    }
    @GetMapping("district/{districtID}/route")
    public ResponseEntity<List<Route>> GetRoutes(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Route>>(routeService.GetRoutes(districtID),HttpStatus.OK);
    }
    @GetMapping("district/{districtID}/station")
    public ResponseEntity<List<Station>> GetStations(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Station>>(routeService.GetStations(districtID),HttpStatus.OK);
    }
    @GetMapping("station/{stationID}")
    public ResponseEntity<StationResponse> GetStationInfo(@PathVariable("stationID") long stationID){
        return new ResponseEntity<StationResponse>(routeService.GetStation(stationID),HttpStatus.OK);
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
}

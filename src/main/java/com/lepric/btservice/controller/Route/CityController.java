package com.lepric.btservice.controller.Route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.service.CityService;
import com.lepric.btservice.service.RouteService;
import com.lepric.btservice.service.StationService;

@RestController
@RequestMapping("/")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private StationService stationService;
    @Autowired
    private RouteService routeService;

    @GetMapping("/city")
    public ResponseEntity<List<City>> getCities() {
        return new ResponseEntity<List<City>>(cityService.GetCities(),HttpStatus.OK) ;
    }
    @GetMapping("city/{cityID}")
    public ResponseEntity<City> getCity(@PathVariable("cityID") long cityID) {
        return new ResponseEntity<City>(cityService.GetCity(cityID),HttpStatus.OK) ;
    }
    @GetMapping("city/{cityID}/district")
    public ResponseEntity<List<District>> getDistricts(@PathVariable("cityID") long cityID) {
        return new ResponseEntity<List<District>>(cityService.GetDistricts(cityID),HttpStatus.OK) ;
    }
    @GetMapping("district/{districtID}/")
    public ResponseEntity<District> getDistrict(@PathVariable("districtID") long districtID) {
        return new ResponseEntity<District>(cityService.GetDistrict(districtID),HttpStatus.OK) ;
    }
    @GetMapping("district/{districtID}/station")
    public ResponseEntity<List<Station>> GetStations(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Station>>(stationService.GetStations(districtID),HttpStatus.OK);
    }
    @GetMapping("district/{districtID}/route")
    public ResponseEntity<List<Route>> GetRoutes(@PathVariable("districtID") long districtID){
        return new ResponseEntity<List<Route>>(routeService.GetRoutes(districtID),HttpStatus.OK);
    }

}

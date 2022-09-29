package com.lepric.btservice.controller.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.payload.response.LocationResponse;
import com.lepric.btservice.service.CityService;
import com.lepric.btservice.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    //Update User Location by userID
    @PutMapping("user/{userID}")
    public ResponseEntity<LocationResponse> UpdateUserLocation(@PathVariable("userID") long userID, @RequestBody LocationResponse location) {
        return new ResponseEntity<LocationResponse>(locationService.UpdateUserLocation(location,userID),HttpStatus.OK) ;
    }

    //Get User location by userID
    @GetMapping("user/{userID}")
    public ResponseEntity<LocationResponse> GetUserLocation(@PathVariable("userID") long userID) {
        return new ResponseEntity<LocationResponse>(locationService.GetUserLocation(userID),HttpStatus.OK) ;
    }

    //Get Bus location by busID
    @GetMapping("bus/{busID}")
    public ResponseEntity<LocationResponse> getBusLocaiton(@PathVariable("busID") long busID) {
        return new ResponseEntity<LocationResponse>(locationService.getBusLocation(busID),HttpStatus.OK) ;
    }
    //Update Bus Location by busID
    @PutMapping("bus/{busID}")
    public ResponseEntity<LocationResponse> UpdateBusLocation(@PathVariable("busID") long userID, @RequestBody LocationResponse location) {
        return new ResponseEntity<LocationResponse>(locationService.UpdateBusLocation(location,userID),HttpStatus.OK) ;
    }
}

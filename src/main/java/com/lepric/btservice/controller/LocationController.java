package com.lepric.btservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.ModelHelper.LocationHelper;
import com.lepric.btservice.service.BusService;
import com.lepric.btservice.service.UserService;

@RestController
@RequestMapping("/location")
public class LocationController {
    
    @Autowired
    private BusService busService;
    
    @Autowired
    private UserService userService;

     //Update User Location by userID
    @PutMapping("user/{userID}")
    public ResponseEntity<LocationHelper> UpdateUserLocation(@PathVariable("userID") long userID, @RequestBody LocationHelper location) {
        return new ResponseEntity<LocationHelper>(userService.UpdateUserLocation(location,userID),HttpStatus.OK) ;
    }

    //Get User location by userID
    @GetMapping("user/{userID}")
    public ResponseEntity<LocationHelper> GetUserLocation(@PathVariable("userID") long userID) {
        return new ResponseEntity<LocationHelper>(userService.GetUserLocation(userID),HttpStatus.OK) ;
    }

    //Get Bus location by busID
    @GetMapping("bus/{busID}")
    public ResponseEntity<LocationHelper> getBusLocaiton(@PathVariable("busID") long busID) {
        return new ResponseEntity<LocationHelper>(busService.getBusLocation(busID),HttpStatus.OK) ;
    }
}

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

import com.lepric.btservice.ModelHelper.LocationModelHelper;
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
    public ResponseEntity<LocationModelHelper> UpdateUserLocation(@PathVariable("userID") long userID, @RequestBody LocationModelHelper location) {
        return new ResponseEntity<LocationModelHelper>(userService.UpdateUserLocation(location,userID),HttpStatus.OK) ;
    }

    //Get User location by userID
    @GetMapping("user/{userID}")
    public ResponseEntity<LocationModelHelper> GetUserLocation(@PathVariable("userID") long userID) {
        return new ResponseEntity<LocationModelHelper>(userService.GetUserLocation(userID),HttpStatus.OK) ;
    }

    //Get Bus location by busID
    @GetMapping("bus/{busID}")
    public ResponseEntity<LocationModelHelper> getBusLocaiton(@PathVariable("busID") long busID) {
        return new ResponseEntity<LocationModelHelper>(busService.getBusLocation(busID),HttpStatus.OK) ;
    }
}

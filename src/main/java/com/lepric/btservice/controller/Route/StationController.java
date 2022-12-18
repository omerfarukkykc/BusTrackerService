package com.lepric.btservice.controller.Route;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.config.SecurityConfig;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.StationResponse;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.StationService;
import com.lepric.btservice.service.UserService;

@RestController
@RequestMapping("/station")
public class StationController {
    /*
     * Get Stations routes
     * Add Station
     * Delete Station
     * Update Station
     * Get Station occupancy
     */
    @Autowired
    StationService stationService;
    @Autowired
    UserService userService;
    @Autowired
    SecurityConfig securityConfig;
    @GetMapping("{stationID}")
    public ResponseEntity<StationResponse> GetStationInfo(@PathVariable("stationID") long stationID){
        return new ResponseEntity<StationResponse>(stationService.GetStation(stationID),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Station>> GetStations(){
        User user =  securityConfig.GetAuthenticatedUser();
        return new ResponseEntity<List<Station>>(stationService.GetStations(user.getDistrict().getDistrictID()),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<StationResponse> AddStation(@PathVariable("stationID") long stationID){
        return null;
    }
    @DeleteMapping("/")
    public ResponseEntity<StationResponse> DeleteStation(@PathVariable("stationID") long stationID){
        return null;
    }
    @PutMapping("/")
    public ResponseEntity<StationResponse> UpdateStation(@PathVariable("stationID") long stationID){
        return null;
    }
    
}

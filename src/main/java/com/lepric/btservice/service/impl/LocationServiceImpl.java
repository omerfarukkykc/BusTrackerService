package com.lepric.btservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import com.lepric.btservice.ModelHelper.LocationModelHelper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.User;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.LocationService;
@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    BusRepository busRepository;
    
    @Autowired
    UserRepository userRepository;
    //Update user location
    @Override
    public LocationModelHelper UpdateUserLocation(LocationModelHelper location, Long userID) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        dbUser.getLocation().setLocation(new Point<G2D>(g(location.getLatitude(),location.getLongitude()),WGS84));
        userRepository.save(dbUser);
        return new LocationModelHelper(dbUser.getLocation().getLocation(),dbUser.getLocation().getUpdatedAt());
    }

    //Get User Location
    @Override
    public LocationModelHelper GetUserLocation(Long userID) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        return new LocationModelHelper(dbUser.getLocation().getLocation(),dbUser.getLocation().getUpdatedAt());
    }
    @Override
    public LocationModelHelper UpdateBusLocation(LocationModelHelper location, Long busID) {
        Bus bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        bus.getLocation().setLocation(new Point<G2D>(g(location.getLatitude(),location.getLongitude()),WGS84));
        busRepository.save(bus);
        return new LocationModelHelper(bus.getLocation().getLocation(),bus.getLocation().getUpdatedAt());
    }

    @Override
    public LocationModelHelper getBusLocation(Long busID) {
        Bus bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        return new LocationModelHelper(bus.getLocation().getLocation(),bus.getLocation().getUpdatedAt());
    }
}
package com.lepric.btservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import java.util.List;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.LocationResponse;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    BusRepository busRepository;

    @Autowired
    UserRepository userRepository;

    // Update user location
    @Override
    public LocationResponse UpdateUserLocation(LocationResponse location, Long userID) {
        User dbUser = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        dbUser.getLocation().setLocation(new Point<G2D>(g(location.getLatitude(), location.getLongitude()), WGS84));
        userRepository.save(dbUser);
        return new LocationResponse(dbUser);
    }

    // Get User Location
    @Override
    public LocationResponse GetUserLocation(Long userID) {
        User dbUser = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        return new LocationResponse(dbUser);
    }

    @Override
    public LocationResponse UpdateBusLocation(LocationResponse location, Long busID) {
        Bus bus = busRepository.findById(busID).orElseThrow(
                () -> new ResourceNotFoundException("Bus", "ID", busID));
        bus.getLocation().setLocation(new Point<G2D>(g(location.getLatitude(), location.getLongitude()), WGS84));
        double minimumDistance = 0.0;
        Station nearestStation = null;
        LocationResponse nearestStationLocation = null;
        for (Station station : bus.getRoute().getStations()) {
            if (nearestStation == null) {
                nearestStation = station;
                nearestStationLocation = new LocationResponse(station.getLocation().getLocation());
                minimumDistance = CalcDistanceMt(nearestStationLocation.getLatitude(),
                        nearestStationLocation.getLongitude(), location.getLatitude(), location.getLongitude());
            } else {
                nearestStationLocation = new LocationResponse(station.getLocation().getLocation());
                double distance = CalcDistanceMt(nearestStationLocation.getLatitude(),
                        nearestStationLocation.getLongitude(), location.getLatitude(), location.getLongitude());
                if (distance < minimumDistance) {
                    nearestStation = station;
                    nearestStationLocation = new LocationResponse(station.getLocation().getLocation());
                    minimumDistance = distance;
                }
            }
        }
       
        if (minimumDistance < (double) nearestStation.getStationScope()) {
            bus.setCurrentStation(nearestStation);
        }   
        busRepository.save(bus);
        return new LocationResponse(bus);
    }

    @Override
    public LocationResponse getBusLocation(Long busID) {
        Bus bus = busRepository.findById(busID).orElseThrow(
                () -> new ResourceNotFoundException("Bus", "ID", busID));
        return new LocationResponse(bus);
    }

    private static double CalcDistanceMt(double lat1, double lon1, double lat2, double lon2) {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                        * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return (c * r) * 1000;
    }
    
}

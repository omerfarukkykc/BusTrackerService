package com.lepric.btservice.service.impl;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.payload.response.StationResponse;
import com.lepric.btservice.repository.RouteRepository;
import com.lepric.btservice.repository.StationRepository;
import com.lepric.btservice.service.StationService;

@Service
public class StationServiceImpl implements StationService{
    @Autowired
    StationRepository stationRepository;
    @Autowired
    RouteRepository routeRepository;
    @Override
    public StationResponse GetStation(long stationID) {
        Station station = stationRepository.findById(stationID).orElseThrow(
            () -> new ResourceNotFoundException("Route", "routeID",stationID)
        );
        List<Route> routes = new ArrayList<Route>();
        routeRepository.findAll().forEach(item->{
            if(item.getStations().contains(station)){
                routes.add(item);
            }
        });
        
        return new StationResponse(station, routes);
    }

    @Override
    public List<Station> GetStations(long districtID) {
        //Todo iyileştiriebilir.
        List<Station> stations= stationRepository.findAll();
        List<Station> response= new ArrayList<Station>();
        for (Station station : stations) {
            if(station.getDistrict().getDistrictID() == districtID){
                response.add(station);
            }
        }
        return response;
    }

    @Override
    public Station AddStation(Station station) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean DeleteStation(long stationID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Station UpdateStation(Station station) {
        // TODO Auto-generated method stub
        return null;
    }
}

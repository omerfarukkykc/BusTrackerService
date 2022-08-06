package com.lepric.btservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.repository.CityRepository;
import com.lepric.btservice.repository.RouteRepository;
import com.lepric.btservice.repository.StationRepository;
import com.lepric.btservice.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<Route> GetRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route AddRoute(Route route) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Route> GetRoutes(long districtID) {
        List<Route> list = new ArrayList<Route>();
        List<Route> routeList = routeRepository.findAll();
        for (Route route : routeList) {
            if(route.getDistrict().getDistrictID() == districtID){
                list.add(route);
            }
        }
        return list;
    }

    @Override
    public Route GetRoute(long routeID) {
        Route route = routeRepository.findById(routeID).orElseThrow(
            () -> new ResourceNotFoundException("Route", "routeID", routeID)
        );
        return route;
    }

    @Override
    public List<Station> GetRouteStations(long routeID) {
        Route route = routeRepository.findById(routeID).orElseThrow(
            () -> new ResourceNotFoundException("Route", "routeID", routeID)
        );
        return route.getStations();
    }

    @Override
    public List<Route> GetRouteRealTimeData(long routeID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Station GetStation(long stationID) {
        Station station = stationRepository.findById(stationID).orElseThrow(
            () -> new ResourceNotFoundException("Route", "routeID",stationID)
        );
        return station;
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
    
}

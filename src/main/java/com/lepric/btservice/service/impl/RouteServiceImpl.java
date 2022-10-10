package com.lepric.btservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.payload.response.LocationResponse;
import com.lepric.btservice.payload.response.RouteResponse;
import com.lepric.btservice.payload.response.StationResponse;
import com.lepric.btservice.repository.BusRepository;
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
    @Autowired
    BusRepository busRepository;

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
    public RouteResponse GetRoute(long routeID) {
        Route route = routeRepository.findById(routeID).orElseThrow(
           
        );
        List<Bus> busses = new ArrayList<Bus>();
        busRepository.findAll().forEach((item)->{
            if(item.getRoute().getRouteID()==route.getRouteID()&&item.getIsActive()){
                busses.add(item);
            }
        });
        RouteResponse response = new RouteResponse();
        response.setBusses(busses);
        response.setRouteName(route.getRouteName());
        response.setRouteID(routeID);
        response.setStations(route.getStations());
        response.setUpdatedAt(route.getUpdatedAt());
        response.setRouteLineG(new ArrayList<LocationResponse>());
        response.setRouteLineD(new ArrayList<LocationResponse>());
        route.getRouteLineG().forEach((item)->{
            response.getRouteLineG().add(new LocationResponse(item.getLocation(),item.getUpdatedAt(),item.getSequence()));
        });
        route.getRouteLineD().forEach((item)->{
            response.getRouteLineD().add(new LocationResponse(item.getLocation(),item.getUpdatedAt(),item.getSequence()));
        });
        return response;
    }

    @Override
    public List<Station> GetRouteStations(long routeID) {
        Route route = routeRepository.findById(routeID).orElseThrow(
            () -> new ResourceNotFoundException("Route", "routeID", routeID)
        );
        return route.getStations();
    }

    @Override
    public List<Bus> GetRouteRealTimeData(long routeID) {
        Route route = routeRepository.findById(routeID).orElseThrow(
           
        );
        List<Bus> busses = new ArrayList<Bus>();
        busRepository.findAll().forEach((item)->{
            if(item.getRoute().getRouteID()==route.getRouteID()&&item.getIsActive()){
                busses.add(item);
            }
        });
        return busses;
    }

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
    
}

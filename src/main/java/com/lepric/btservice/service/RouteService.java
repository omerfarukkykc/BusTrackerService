package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;

public interface RouteService {
    List<Route> GetRoutes();
    Route GetRoute(long routeID);
    List<Station> GetRouteStations(long routeID);
    List<Route> GetRouteRealTimeData(long routeID);
    List<Route> GetRoutes(long districtID);

    Station GetStation(long stationID);
    List<Station> GetStations(long districtID);



    Route AddRoute(Route route);
    
}

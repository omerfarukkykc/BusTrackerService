package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.RouteTime;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.payload.response.RouteResponse;

public interface RouteService {
    List<Route> GetRoutes();
    RouteResponse GetRouteResponse(long routeID);
    Route GetRoute(long routeID);
    List<Station> GetRouteStations(long routeID);
    List<RouteTime> GetRouteTimes(long routeID);
    List<Bus> GetRouteRealTimeData(long routeID);
    List<Route> GetRoutes(long districtID);
    Route AddRoute(Route route);
    
}

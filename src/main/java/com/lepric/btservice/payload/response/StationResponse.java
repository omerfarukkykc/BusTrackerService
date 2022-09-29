package com.lepric.btservice.payload.response;


import java.util.ArrayList;
import java.util.List;

import com.lepric.btservice.model.Station;

import lombok.Data;

@Data
public class StationResponse {
    private long stationID;

    private String stationName;

    private LocationResponse location;

    private float stationScope;
    
    private String direction;
    
    private List<StationResponse.Route> routes;

    public StationResponse() {
    }

    public StationResponse(Station station,List<com.lepric.btservice.model.Route> routes) {
        this.stationID = station.getStationID();
        this.stationName = station.getStationName();
        this.direction = station.getDirection();
        this.stationScope = station.getStationScope();
        this.location = new LocationResponse(station.getLocation().getLocation());
        this.routes = new ArrayList<StationResponse.Route>();
        routes.forEach(item->{
            this.routes.add(new Route(item.getRouteID(),item.getRouteName()));
        });


    }
    @Data
    public class Route{
        private long routeID;
        private String routeName;
        public Route(long routeID, String routeName) {
            this.routeID = routeID;
            this.routeName = routeName;
        }
    }
}

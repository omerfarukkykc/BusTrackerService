package com.lepric.btservice.payload.response;


import java.util.ArrayList;
import java.util.List;

import com.lepric.btservice.model.Bus;
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

    private List<StationResponse.Route> activeRoutes;


    public StationResponse() {
    }

    public StationResponse(Station station,List<com.lepric.btservice.model.Route> routes) {
        this.stationID = station.getStationID();
        this.stationName = station.getStationName();
        this.direction = station.getDirection();
        this.stationScope = station.getStationScope();
        this.location = new LocationResponse(station.getLocation().getLocation());
        this.routes = new ArrayList<StationResponse.Route>();
        this.activeRoutes = new ArrayList<StationResponse.Route>();
        for (com.lepric.btservice.model.Route route : routes) {
            for(Bus bus : route.getBusses()){
                if(bus.getIsActive()){


                    List<LocationResponse> locationResponses = new ArrayList<LocationResponse>();
                    
                    boolean flag = false;
                    for (Station item : route.getStations()){
                        if(item.getStationID() == bus.getCurrentStation().getStationID()){
                            flag = true;
                        }else if (item.getStationID() == station.getStationID()){
                            flag = false;
                        }
                        if(flag){
                            locationResponses.add(new LocationResponse(item.getLocation().getLocation()));
                        }
                    }
                    double remainingTime = CalcRemainingTime(locationResponses, bus.getSpeed());

                    this.activeRoutes.add(new Route(route.getRouteID(),route.getRouteName(),remainingTime,route.getActiveBusses()));
                    break;
                }
            }
        }
        
        
        routes.forEach(item->{
            this.routes.add(new Route(item.getRouteID(),item.getRouteName()));
        });


    }
    private static double CalcDistanceKm(double lat1, double lon1, double lat2, double lon2) {
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
        return (c * r);
    }

    private static double CalcDistanceLineer(List<LocationResponse> polygon) {
        double distancee = 0.0;
        for (int i = 0; i < polygon.size() - 1; i++) {
            distancee += CalcDistanceKm(polygon.get(i).getLatitude(), polygon.get(i).getLongitude(),
                    polygon.get(i + 1).getLatitude(), polygon.get(i + 1).getLongitude());
        }
        return distancee;
    }
    public static double CalcRemainingTime(List<LocationResponse> polygon,double speed) {
        return CalcDistanceLineer(polygon)/speed;
    }
    
    @Data
    public class Route{
        private long routeID;
        private String routeName;
        private double remainingTime;
        private List<Bus> busses;
        public Route(long routeID, String routeName, double remainingTime, List<Bus> busses) {
            this.routeID = routeID;
            this.routeName = routeName;
            this.remainingTime = remainingTime;
            this.busses = busses;
        }
        public Route(long routeID, String routeName) {
            this.routeID = routeID;
            this.routeName = routeName;
        }
    }
}

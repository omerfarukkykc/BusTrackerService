package com.lepric.btservice.payload.response;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Station;

import lombok.Data;

@Data
public class RouteResponse {

    private long routeID;
    
    private LocalDateTime updatedAt;
    
    private String routeName;


    private List<LocationResponse> routeLineG;

    private List<LocationResponse> routeLineD;

    private List<StationResponse> stations;

    private List<Bus> busses;
    public void setStations(List<Station> station){
        this.stations = new ArrayList<StationResponse>();
        int i = 0;
        for(Station item :station){
            i++;
            StationResponse stationResponse = new StationResponse();
            stationResponse.setStationID(item.getStationID());
            stationResponse.setStationName(item.getStationName());
            stationResponse.setStationScope(item.getStationScope());
            stationResponse.setLocation(new LocationResponse(item.getLocation().getLocation(),i));
            stationResponse.setDirection(item.getDirection());
            this.stations.add(stationResponse);

        }
        
    }
}

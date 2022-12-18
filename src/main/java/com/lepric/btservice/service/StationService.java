package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.Station;
import com.lepric.btservice.payload.response.StationResponse;

public interface StationService {
    StationResponse GetStation(long stationID);
    List<Station> GetStations(long districtID);
    Station AddStation(Station station);
    boolean DeleteStation(long stationID);
    Station UpdateStation(Station station);
}   

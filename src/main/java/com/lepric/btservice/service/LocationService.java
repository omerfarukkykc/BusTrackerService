package com.lepric.btservice.service;


import com.lepric.btservice.payload.response.LocationResponse;



public interface LocationService {

    LocationResponse UpdateUserLocation(LocationResponse location, Long userID);
    LocationResponse GetUserLocation(Long userID);

    LocationResponse UpdateBusLocation(LocationResponse location, Long busID);
    LocationResponse getBusLocation(Long busID);
}

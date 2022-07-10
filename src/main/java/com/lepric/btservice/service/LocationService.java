package com.lepric.btservice.service;


import com.lepric.btservice.ModelHelper.LocationModelHelper;



public interface LocationService {

    LocationModelHelper UpdateUserLocation(LocationModelHelper location, Long userID);
    LocationModelHelper GetUserLocation(Long userID);

    LocationModelHelper UpdateBusLocation(LocationModelHelper location, Long busID);
    LocationModelHelper getBusLocation(Long busID);
}

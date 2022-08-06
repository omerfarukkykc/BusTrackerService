package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;
import com.lepric.btservice.model.Route;

public interface CityService {
    List<City> GetCities();
    City GetCity(long cityID);
    District GetDistrict(long districtID);
    List<District> GetDistricts(long cityID);
    List<Route> GetRoutes(long districtID);
    
}

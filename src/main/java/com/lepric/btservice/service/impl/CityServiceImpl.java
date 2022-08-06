package com.lepric.btservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.repository.CityRepository;
import com.lepric.btservice.repository.DistrictRepository;
import com.lepric.btservice.service.CityService;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;
    
    @Override
    public List<City> GetCities() {
        return cityRepository.findAll();
        //Todo Burası Yapılacak
    }

    @Override
    public List<District> GetDistricts(long cityID) {
        City city = cityRepository.findById(cityID).orElseThrow(
            () -> new ResourceNotFoundException("City", "cityID", cityID)
        );
        return city.getDistricts();
    }

    @Override
    public List<Route> GetRoutes(long districtID) {
        District district = districtRepository.findById(districtID).orElseThrow(
            () -> new ResourceNotFoundException("District", "districtID", districtID)
        );
        return district.getRoutes();
    }

    @Override
    public City GetCity(long cityID) {
        return cityRepository.findById(cityID).orElseThrow(
            () -> new ResourceNotFoundException("City", "cityID", cityID)
        );
    }

    @Override
    public District GetDistrict(long districtID) {
        return districtRepository.findById(districtID).orElseThrow(
            () -> new ResourceNotFoundException("District", "districtID", districtID)
        );
    }
}

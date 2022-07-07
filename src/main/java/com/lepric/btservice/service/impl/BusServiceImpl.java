package com.lepric.btservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lepric.btservice.ModelHelper.LocationHelper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Busses;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.service.BusService;

@Service
public class BusServiceImpl implements BusService{
    private BusRepository busRepository;
    public BusServiceImpl(BusRepository busRepository) {
        super();
        this.busRepository = busRepository;
    }
    @Override
    public LocationHelper getBusLocation(long busID) {
        Busses bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        return new LocationHelper(bus.getLocation().getLocation(),bus.getLocation().getUpdatedAt());
    }
    @Override
    public boolean Deletebus(long busID) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public Busses Getbus(long busID) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<Busses> Getbusses() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Busses UpdateBus(Busses bus, long busID) {
        // TODO Auto-generated method stub
        return null;
    }
}

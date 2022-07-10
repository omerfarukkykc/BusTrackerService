package com.lepric.btservice.service.impl;

import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;


import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Location;
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
    public boolean DeleteBus(long busID) {
        Bus bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        busRepository.delete(bus);
        return true;
    }
    @Override
    public Bus GetBus(long busID) {
        return  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
    }
    @Override
    public List<Bus> GetBusses() {
        return busRepository.findAll();
    }
    @Override
    public Bus UpdateBus(Bus bus, long busID) {
        Bus dbBus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        if(dbBus.getPlate() != null)    
            dbBus.setPlate(bus.getPlate());
        if(dbBus.getModel() != null)
            dbBus.setModel(bus.getModel());  
        return busRepository.save(dbBus);
    }
    @Override
    public Bus AddBus(Bus bus) {
        bus.setLocation(new Location());
        bus.getLocation().setLocation(new Point<G2D>(g(0,0),WGS84));
        return busRepository.save(bus);
    }
    
}

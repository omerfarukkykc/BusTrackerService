package com.lepric.btservice.service.impl;

import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.ModelHelper.BusModelHelper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusModel;
import com.lepric.btservice.model.BusModelBrands;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.repository.BusModelBrandsRepository;
import com.lepric.btservice.repository.BusModelRepository;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.service.BusService;

@Service
public class BusServiceImpl implements BusService{
    
    @Autowired
    private BusRepository busRepository;
    
    @Autowired
    private BusModelRepository busModelRepository;

    @Autowired
    private BusModelBrandsRepository busModelBrandsRepository;
    
    
    @Override
    public boolean DeleteBus(long busID) {
        Bus bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        busRepository.delete(bus);
        return true;
    }
    @Override
    public BusModelHelper GetBus(long busID) {
        Bus bus = busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        return new BusModelHelper(bus);    
    }
    @Override
    public List<Bus> GetBusses() {
        return busRepository.findAll();
    }
    @Override
    public BusModelHelper UpdateBus(Bus bus, long busID) {
        Bus dbBus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        if(dbBus.getPlate() != null)    
            dbBus.setPlate(bus.getPlate());
        if(dbBus.getModel() != null)
            dbBus.setModel(bus.getModel());  
        return new BusModelHelper(busRepository.save(dbBus));
    }
    @Override
    public BusModelHelper AddBus(BusModelHelper busHelper) {
        Bus bus = busHelper.toBus();
        bus.setLocation(new Location());
        bus.getLocation().setLocation(new Point<G2D>(g(0,0),WGS84));
        return new BusModelHelper(busRepository.save(bus));
    }

    @Override
    public List<BusModel> getModels() {
        return busModelRepository.findAll();
    }

    @Override
    public List<BusModelBrands> getModelBrands() {
        return busModelBrandsRepository.findAll();
    }
    
}

package com.lepric.btservice.service.impl;

import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.ModelHelper.BusModelHelper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusBrand;
import com.lepric.btservice.model.BusBrandModel;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.repository.BusBrandsRepository;
import com.lepric.btservice.repository.BusBrandModelRepository;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.service.BusService;

@Service
public class BusServiceImpl implements BusService{
    
    @Autowired
    private BusRepository busRepository;
    
    @Autowired
    private BusBrandModelRepository busBrandModelRepository;

    @Autowired
    private BusBrandsRepository busBrandRepository;
    
    
    @Override
    public boolean DeleteBus(long busID) {
        Bus bus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        busRepository.delete(bus);
        return true;
    }
    @Override
    public Bus GetBus(long busID) {
        Bus bus = busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        return bus;    
    }
    @Override
    public List<Bus> GetBusses() {
        return busRepository.findAll();
    }
    @Override
    public Bus UpdateBus(BusModelHelper busHelper, long busID) {
        Bus dbBus =  busRepository.findById(busID).orElseThrow(
            () -> new ResourceNotFoundException("Bus", "ID", busID));
        if(busHelper.getPlate() != null)    
            dbBus.setPlate(busHelper.getPlate());
        if(busHelper.getBrandID() != 0)
            dbBus.setBrand(busBrandRepository.getById(busHelper.getBrandID()));
        if(busHelper.getModelID() != 0)
            dbBus.setModel(busBrandModelRepository.getById(busHelper.getModelID()));
        return busRepository.save(dbBus);
    }
    @Override
    public Bus AddBus(BusModelHelper busHelper) {
        Bus bus = busHelper.toBus();
        bus.setBrand(busBrandRepository.getById(busHelper.getBrandID()));
        bus.setModel(busBrandModelRepository.getById(busHelper.getModelID()));
        bus.setLocation(new Location());
        bus.getLocation().setLocation(new Point<G2D>(g(0,0),WGS84));
        return busRepository.save(bus);
    }

    @Override
    public List<BusBrand> getBrands() {
        return busBrandRepository.findAll();
    }

    @Override
    public List<BusBrandModel> getBrandModels(long brandID) {
        BusBrand brand = busBrandRepository.findById(brandID).orElseThrow(
            () -> new ResourceNotFoundException("Brand", "ID", brandID));
        return brand.getModels();
    }
    @Override
    public BusBrand addModel(BusBrand busModel) {
         // TODO Auto-generated method stub
         return null;
    }
    @Override
    public boolean deleteModel() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean updateModel() {
        // TODO Auto-generated method stub
        return false;
    }
    
}

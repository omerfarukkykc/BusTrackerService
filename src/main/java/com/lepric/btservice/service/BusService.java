package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusBrand;
import com.lepric.btservice.model.BusBrandModel;
import com.lepric.btservice.payload.request.BusRequest;

public interface BusService {


    boolean DeleteBus(long busID);

    Bus GetBus(long busID);

    List<Bus> GetBusses();

    Bus UpdateBus(BusRequest bus, long busID);

    Bus AddBus(BusRequest busHelper);

    
    List<BusBrand> getBrands();

    List<BusBrandModel> getBrandModels(long brandID);
    
    BusBrand addBusBrand(BusBrand busBrand);
    
    BusBrandModel addBusBrandModel(BusBrandModel busBrandModel);
    
    
    boolean deleteBrandModel();
    boolean updateBrandModel();

    Boolean setActive(long busID, boolean isActive);

    

    
}

package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.ModelHelper.BusModelHelper;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusModel;
import com.lepric.btservice.model.BusModelBrands;

public interface BusService {


    boolean DeleteBus(long busID);

    BusModelHelper GetBus(long busID);

    List<Bus> GetBusses();

    BusModelHelper UpdateBus(Bus bus, long busID);

    BusModelHelper AddBus(BusModelHelper busHelper);

    
    List<BusModel> getModels();

    List<BusModelBrands> getModelBrands();
    
    

    

    
}

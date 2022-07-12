package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.ModelHelper.BusModelHelper;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusBrand;
import com.lepric.btservice.model.BusBrandModel;

public interface BusService {


    boolean DeleteBus(long busID);

    Bus GetBus(long busID);

    List<Bus> GetBusses();

    Bus UpdateBus(BusModelHelper bus, long busID);

    Bus AddBus(BusModelHelper busHelper);

    
    List<BusBrand> getBrands();
    BusBrand addModel(BusBrand busModel);
    boolean deleteModel();
    boolean updateModel();


    List<BusBrandModel> getBrandModels(long brandID);
    
    

    

    
}

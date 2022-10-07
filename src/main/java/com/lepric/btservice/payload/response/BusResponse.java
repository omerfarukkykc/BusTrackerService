package com.lepric.btservice.payload.response;

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.BusBrand;
import com.lepric.btservice.model.BusBrandModel;

import lombok.Data;

@Data
public class BusResponse {

    private long busID;

    private String plate;
    
    private BusBrand brand;

    private BusBrandModel model;

    private boolean isActive;
    
    private LocationResponse location;

    public BusResponse(Bus bus) {
        this.busID = bus.getBusID();
        this.plate = bus.getPlate();
        this.brand = bus.getBrand();
        this.model = bus.getModel();
        this.isActive = bus.getIsActive();
        this.location = new LocationResponse(bus.getLocation().getLocation());
    }
    
}

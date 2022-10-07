package com.lepric.btservice.payload.request;


import com.lepric.btservice.model.Bus;

import lombok.Data;

@Data
public class BusRequest {

    private long busID;

    private String plate;

    private long routeID;
    
    private long brandID;

    private long modelID;

    private boolean isActive;

    public BusRequest() {
    }
    public Bus toBus() {
        Bus bus = new Bus();
        bus.setIsActive(this.isActive);
        bus.setPlate(this.plate);
        return bus;
    }
}


package com.lepric.btservice.payload.response;


import com.lepric.btservice.model.Bus;

import lombok.Data;

@Data
public class BusModelHelper {

    private long busID;

    private String plate;

    private long brandID;

    private long modelID;

    public BusModelHelper() {
    }
    public Bus toBus() {
        Bus bus = new Bus();
        bus.setPlate(this.plate);
        return bus;
    }
}


package com.lepric.btservice.ModelHelper;

import com.lepric.btservice.model.Bus;

import lombok.Data;

@Data
public class BusModelHelper {

    private long busID;

    private String plate;

    private String brand;

    private String model;

    public BusModelHelper() {
    }
    public BusModelHelper(Bus bus) {
        this.busID = bus.getBusID();
        this.model = bus.getModel().getModelName();
        this.plate = bus.getPlate();
        this.brand = bus.getModel().getBrand().getBrandName();
    }
    public Bus toBus() {
        Bus bus = new Bus();
        bus.getModel().setModelName(this.model);
        bus.getModel().getBrand().setBrandName(this.brand);
        bus.setPlate(this.plate);
        return bus;
    }
}


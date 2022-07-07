package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.ModelHelper.LocationModelHelper;
import com.lepric.btservice.model.Bus;

public interface BusService {
    LocationModelHelper getBusLocation(long busID);

    boolean Deletebus(long busID);

    Bus Getbus(long busID);

    List<Bus> Getbusses();

    Bus UpdateBus(Bus bus, long busID);

    Bus Addbus(Bus bus);
}

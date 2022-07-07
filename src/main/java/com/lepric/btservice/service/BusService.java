package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.ModelHelper.LocationHelper;
import com.lepric.btservice.model.Bus;

public interface BusService {
    LocationHelper getBusLocation(long busID);

    boolean Deletebus(long busID);

    Bus Getbus(long busID);

    List<Bus> Getbusses();

    Bus UpdateBus(Bus bus, long busID);
}

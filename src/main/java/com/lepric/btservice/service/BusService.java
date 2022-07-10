package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.model.Bus;

public interface BusService {


    boolean DeleteBus(long busID);

    Bus GetBus(long busID);

    List<Bus> GetBusses();

    Bus UpdateBus(Bus bus, long busID);

    Bus AddBus(Bus bus);

    
}

package com.lepric.btservice.service;

import java.util.List;

import com.lepric.btservice.ModelHelper.LocationHelper;
import com.lepric.btservice.model.Busses;

public interface BusService {
    LocationHelper getBusLocation(long busID);

    boolean Deletebus(long busID);

    Busses Getbus(long busID);

    List<Busses> Getbusses();

    Busses UpdateBus(Busses bus, long busID);
}

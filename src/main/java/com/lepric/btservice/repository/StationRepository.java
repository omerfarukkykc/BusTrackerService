package com.lepric.btservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
    public Optional<Station> findByStationName(String stationName);
}

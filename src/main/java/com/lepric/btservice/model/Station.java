package com.lepric.btservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stationID")
    private long stationID;

    @Column(name = "stationName")
    private String stationName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID",nullable = false,unique = true,updatable = false)
    private Location location;

    @Column(name = "stationScope")
    private float stationScope;

    @JsonIgnore
    @JoinColumn(name = "routeID")
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Route> routes;

    @JsonIgnore
    @JoinColumn(name = "cityID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;

    @Column(name = "direction")
    private String direction;
    
    @JsonIgnore
    @JoinColumn(name = "districtID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private District district;
}

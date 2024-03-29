package com.lepric.btservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "District")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "districtID")
    private long districtID;

    @Column(name = "districtName",length = 20,nullable = false)
    private String districtName;

    @Column(name = "isActive")
    private boolean isActive;

    @JsonIgnore
    //@JoinColumn(name = "routeID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;

    @JsonIgnore
    @JoinColumn(name = "routeID")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Route> routes;

    @JsonIgnore
    @JoinColumn(name = "stationID")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Station> stations;

}

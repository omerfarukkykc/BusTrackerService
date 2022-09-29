package com.lepric.btservice.model;

import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name = "Route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeID")
    private long routeID;
    
    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true, nullable = false)
    private LocalDateTime updatedAt;
    
    @Column(name = "routeName") 
    private String routeName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> routeLineG;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> routeLineD;
    
    @JsonIgnore
    @JoinColumn(name = "stationID")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Station> stations;

    @JsonIgnore
    @JoinColumn(name = "busID")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bus> busses;

    @JsonIgnore
    @JoinColumn(name = "districtID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private District district;

    @JsonIgnore
    @JoinColumn(name = "cityID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;


}

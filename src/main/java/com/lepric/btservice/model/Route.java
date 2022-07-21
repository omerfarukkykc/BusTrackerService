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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.geolatte.geom.Polygon;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.geolatte.geom.G2D;

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
    
    @Column(name = "routeName", columnDefinition = "POLYGON") 
    private String routeName;

    @JsonIgnore
    @Column(name = "route", columnDefinition = "POLYGON") 
    private Polygon<G2D> route;

    @JoinColumn(name = "stationID")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Station> stations;

    @JoinColumn(name = "busID")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bus> busses;


}

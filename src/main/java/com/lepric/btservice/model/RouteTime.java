package com.lepric.btservice.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "RouteTime")
public class RouteTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeTimeID")
    private long routeTimeID;
    
    @Column(name = "startTime", updatable = false, nullable = false)
    private LocalTime startTime = LocalTime.of(7, 0, 0);

    @Column(name = "finishTime", updatable = false, nullable = false)
    private LocalTime finishTime = LocalTime.of(7, 0, 0);

    @JsonIgnore
    @JoinColumn(name = "routeID",unique = false)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Route route;

    public RouteTime() {
    }

    public RouteTime(LocalTime startTime, LocalTime finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    

}

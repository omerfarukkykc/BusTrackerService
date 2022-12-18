package com.lepric.btservice.model;

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
@Table(name = "Favorite")
public class Favorite {
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteID")
    private long favoriteID;

    @JsonIgnore 
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stationID")
    private Station station;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "routeID")
    private Route route;
}

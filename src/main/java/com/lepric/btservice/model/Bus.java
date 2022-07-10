package com.lepric.btservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lepric.btservice.ModelHelper.BusModelHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "Busses")
public class Bus {
      
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "busID")
    private long busID;

    @Column(name = "plate",nullable = false,unique = true)
    private String plate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelID",nullable = false)
    private BusModel model;

    
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID",nullable = false,unique = true,updatable = false)
    private Location location;
}

package com.lepric.btservice.model;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brandID")
    private BusBrand brand;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "modelID")
    private BusBrandModel model;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID",nullable = false,unique = true,updatable = false)
    private Location location;

    

}

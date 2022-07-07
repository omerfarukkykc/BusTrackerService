package com.lepric.btservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "BusModels")
public class BusModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelID")
    private long modeldID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brandID",nullable = false,unique = true,updatable = false)
    private BusModelBrands brand;

    @Column(name = "modelName",nullable = false)
    private String modelName;
    
}

package com.lepric.btservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Busses")
public class Busses {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "busID")
    private long busID;

    @Column(name = "plate",nullable = false,unique = true)
    private String plate;

    @Column(name = "brand",nullable = false,unique = true)
    private String brand;
}

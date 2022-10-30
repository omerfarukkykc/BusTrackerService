package com.lepric.btservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Fee")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeID")
    private long feeID;
    
    @Column(name = "fullFeeValue")
    private double fullFeeValue = 5.0;
    
    @Column(name = "stationFeeValue")
    private double stationFeeValue = 1.2;
    
    @JsonIgnore
    @JoinColumn(name = "feeID")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Route> routes;
}

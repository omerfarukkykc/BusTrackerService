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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "BusBrandModel")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BusBrandModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelID")
    private long modelID;

    @Column(name = "modelName")
    private String modelName;

    @JsonIgnore
    @JoinColumn(name = "brandID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private BusBrand busBrand;
}

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "BusBrand")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BusBrand {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandID")
    private long brandID;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "brandID")
    private List<BusBrandModel> models;

    @Column(name = "brandName",nullable = false)
    private String brandName;
    
}

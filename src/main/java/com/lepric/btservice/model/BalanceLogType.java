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
@Table(name = "balanceLogType")
public class BalanceLogType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balanceLogTypeID")
    private long balanceLogTypeID;

    @Column(name = "balanceLogTypeName")
    private String logTypeName;

    @Column(name = "direction")
    private boolean direction; 
}

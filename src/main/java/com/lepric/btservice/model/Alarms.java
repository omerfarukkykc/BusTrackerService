package com.lepric.btservice.model;

import java.time.LocalDateTime;

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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Alarms")
public class Alarms {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarmID")
    private long alarmID;

    @CreationTimestamp()
    @Column(name = "alarmDateTime", updatable = false, nullable = false)
    private LocalDateTime alarmDateTime; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "busID",nullable = false)
    private Busses bus;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stationID",nullable = false)
    private Stations station;
    
}

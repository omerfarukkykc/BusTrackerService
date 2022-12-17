package com.lepric.btservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "balanceLog")
public class BalanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balanceLogID")
    private long balanceLogID;

    @CreationTimestamp
    @Column(name = "procDate")
    private LocalDateTime procDate;

    @JsonIgnore
    @JoinColumn(name = "userID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "balanceLogTypeID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private BalanceLogType logType;

    @Column(name = "logDescription")
    private String logDdescription;

    @Column(name = "logAmount")
    private double logAmount;

}

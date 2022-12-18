package com.lepric.btservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Pano")
public class Pano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageID")
    private long messageID;

    @Column(name = "messageHeader")
    private String messageHeader;
    
    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "creation_at")
    private LocalDateTime date;

}

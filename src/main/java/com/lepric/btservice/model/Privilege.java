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
@Table(name = "Privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegeID")
    private long privilegeID;

    @Column(name = "privilegeName", nullable = false,length = 40)
    private String privilegeName;

    public Privilege() {
    }

    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }

}

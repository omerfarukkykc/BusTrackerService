
package com.lepric.btservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.geolatte.geom.*;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import lombok.Data;

@Data
@Entity
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationID")
    private long locationID;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true, nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(columnDefinition = "POINT")
    private Point<G2D> location;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "sequence")
    private int sequence;

    public Location( double longitude,double latitude, boolean isActive, int sequence) {
        this.isActive = isActive;
        this.sequence = sequence;
        this.location = new Point<G2D>(g(longitude, latitude), WGS84);
    }

    public Location() {
        this.location = new Point<G2D>(g(0, 0), WGS84);
    }
    public Location(double longitude,double latitude) {
        this.location = new Point<G2D>(g(longitude, latitude), WGS84);
    }
}

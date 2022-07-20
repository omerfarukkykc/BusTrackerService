package com.lepric.btservice.payload.response;



import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.User;

import lombok.Data;

@Data
public class LocationModelHelper {
    private float latitude;
    private float longitude;
    private LocalDateTime updated_at;

    public LocationModelHelper() {
    }
    public LocationModelHelper(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public LocationModelHelper(Bus bus) {
        String wkt = Wkt.toWkt(bus.getLocation().getLocation());
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
       .results()                      
       .map(mr -> mr.group(1))
       .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.latitude = Float.parseFloat(coordinates[0]);
                this.longitude = Float.parseFloat(coordinates[1]);
              }
         );
         this.updated_at = bus.getLocation().getUpdatedAt();
    }
    public LocationModelHelper(User user) {
        
        String wkt = Wkt.toWkt(user.getLocation().getLocation());
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
       .results()                      
       .map(mr -> mr.group(1))
       .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.latitude = Float.parseFloat(coordinates[0]);
                this.longitude = Float.parseFloat(coordinates[1]);
              }
         );
         this.updated_at = user.getLocation().getUpdatedAt();
    }
    public LocationModelHelper(float latitude, float longitude, LocalDateTime updated_at) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.updated_at = updated_at;
    }

    public LocationModelHelper(Point<G2D> point,LocalDateTime updatedAt) {
        String wkt = Wkt.toWkt(point);
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
       .results()                      
       .map(mr -> mr.group(1))
       .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.latitude = Float.parseFloat(coordinates[0]);
                this.longitude = Float.parseFloat(coordinates[1]);
              }
         );
         this.updated_at = updatedAt;
    }
}

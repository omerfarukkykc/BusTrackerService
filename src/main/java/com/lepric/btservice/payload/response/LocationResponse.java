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
public class LocationResponse {
    private float latitude;
    private float longitude;
    private int sequence;
    private LocalDateTime updated_at;

    public LocationResponse() {
    }
    public LocationResponse(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public LocationResponse(Bus bus) {
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
    public LocationResponse(User user) {
        
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
    public LocationResponse(float latitude, float longitude, LocalDateTime updated_at) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.updated_at = updated_at;
    }

    public LocationResponse(Point<G2D> point,LocalDateTime updatedAt) {
        String wkt = Wkt.toWkt(point);
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
       .results()                      
       .map(mr -> mr.group(1))
       .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.longitude = Float.parseFloat(coordinates[0]);
                this.latitude = Float.parseFloat(coordinates[1]);
              }
         );
         this.updated_at = updatedAt;
    }
    public LocationResponse(Point<G2D> point,LocalDateTime updatedAt,int sequence) {
      this.sequence = sequence;
      String wkt = Wkt.toWkt(point);
      Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
      pattern.matcher(wkt)
     .results()                      
     .map(mr -> mr.group(1))
     .forEach(
            s -> {
              String[] coordinates = s.split(" ");
              this.longitude = Float.parseFloat(coordinates[0]);
              this.latitude = Float.parseFloat(coordinates[1]);
            }
       );
       this.updated_at = updatedAt;
      }
      public LocationResponse(Point<G2D> point) {
        String wkt = Wkt.toWkt(point);
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
      .results()                      
      .map(mr -> mr.group(1))
      .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.longitude = Float.parseFloat(coordinates[0]);
                this.latitude = Float.parseFloat(coordinates[1]);
              }
        );
      }
      public LocationResponse(Point<G2D> point,int sequence) {
        this.sequence = sequence;
        String wkt = Wkt.toWkt(point);
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        pattern.matcher(wkt)
      .results()                      
      .map(mr -> mr.group(1))
      .forEach(
              s -> {
                String[] coordinates = s.split(" ");
                this.longitude = Float.parseFloat(coordinates[0]);
                this.latitude = Float.parseFloat(coordinates[1]);
              }
        );
      }
}

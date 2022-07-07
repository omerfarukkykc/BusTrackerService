package com.lepric.btservice.ModelHelper;



import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;


import lombok.Data;

@Data
public class LocationHelper {
    private float latitude;
    private float longitude;
    private LocalDateTime updated_at;

    public LocationHelper() {
    }
    public LocationHelper(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationHelper(float latitude, float longitude, LocalDateTime updated_at) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.updated_at = updated_at;
    }

    public LocationHelper(Point<G2D> point,LocalDateTime updatedAt) {
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

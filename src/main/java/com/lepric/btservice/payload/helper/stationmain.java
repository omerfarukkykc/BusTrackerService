package com.lepric.btservice.payload.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Station;

import lombok.Data;

public class stationmain {
    public static void main(String[] args) throws IOException {
        //C:\Users\eldexar\Desktop\BusTrackerService\src\main\resources\data\city-district.json
        Path fileName = Path.of("C:/Users/eldexar/Desktop/BusTrackerService/src/main/resources/data/stations.json");
        String str = Files.readString(fileName);
        ObjectMapper om = new ObjectMapper();
        Root[] root = om.readValue(str, Root[].class);
        


        List<Station> stations = new ArrayList<>();
        /*
        for(Root item :root){
            Station station = new Station();
            station.setStationName(item.description);
            station.setLocation(new Location());
            Point<G2D> point=null;
            if(item.longitude.equals("null")||item.latitude.equals("null")){
                point = new Point<G2D>(g(0, 0), WGS84);
                station.getLocation().setLatitude(0.0);
                station.getLocation().setLongitude(0.0);
            }else{
                point = new Point<G2D>(g(Double.parseDouble(item.longitude), Double.parseDouble(item.latitude)), WGS84);
                station.getLocation().setLatitude(Double.parseDouble(item.latitude));
                station.getLocation().setLongitude(Double.parseDouble(item.longitude));
            }
            station.getLocation().setLocation(point);
            station.setStationScope(5);
            station.setStationID(item.stationId);
            stations.add(station);
        }
        */
        om.writeValue(new File("D:/stations.json"), stations);
        System.out.println("bittis");
    }
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */
    @Data
    public static class Root{
        public int stationId;
        public String description;
        public String stationType;
        public String altitude;
        public int isActive;
        public String latitude;
        public String longitude;
        public String panelId;
        public Root() {
        }
        public Root(int stationId, String description, String stationType, String altitude, int isActive,
                String latitude, String longitude, String panelId) {
            this.stationId = stationId;
            this.description = description;
            this.stationType = stationType;
            this.altitude = altitude;
            this.isActive = isActive;
            this.latitude = latitude;
            this.longitude = longitude;
            this.panelId = panelId;
        }
    }

}

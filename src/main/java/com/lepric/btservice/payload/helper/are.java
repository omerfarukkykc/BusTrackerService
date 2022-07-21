package com.lepric.btservice.payload.helper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;

import lombok.Data;


public class are {
    public static void main(String[] args) throws IOException {
        //C:\Users\eldexar\Desktop\BusTrackerService\src\main\resources\data\city-district.json
        Path fileName = Path.of("C:/Users/eldexar/Desktop/BusTrackerService/src/main/resources/data/city-district.json");
        String str = Files.readString(fileName);
        ObjectMapper om = new ObjectMapper();
        Root[] root = om.readValue(str, Root[].class);
        List<City> citiesList =new ArrayList<City>();
        for (Root item : root) {
            City city = new City();
            city.setCityName(item.cityName);
            city.setCityID(item.plateID);
            List<District> districtsList = new ArrayList<District>();
            for (String dist : item.districts) {
                District newDist = new District();
                newDist.setDistrictName(dist);
                districtsList.add(newDist);
            }
            city.setDistricts(districtsList);
            citiesList.add(city);
        }

        om.writeValue(new File("D:/city-districtID.json"), citiesList);
        System.out.println("bittis");
    }
    @Data
    public static class Root{
        public String cityName;
        public int plateID;
        public ArrayList<String> districts;
        public Root() {
        }
        public Root(String cityName, int plateID, ArrayList<String> districts) {
            this.cityName = cityName;
            this.plateID = plateID;
            this.districts = districts;
        }
    }
}

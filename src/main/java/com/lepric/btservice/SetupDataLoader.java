
package com.lepric.btservice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.model.User;
import com.lepric.btservice.repository.CityRepository;
import com.lepric.btservice.repository.PrivilegeRepository;
import com.lepric.btservice.repository.RoleRepository;
import com.lepric.btservice.repository.RouteRepository;
import com.lepric.btservice.repository.StationRepository;
import com.lepric.btservice.repository.UserRepository;

import lombok.Data;

@Component
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {
    
    boolean alreadySetup = true;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;
 
 
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
            createCityAndDistrict();
            createStations();
            addRoute("ALADAĞI");
            addRoute("ÇATMACA-SSK HASTANESİ");
            addRoute("DEVLET HASTANESİ");
            addRoute("DOSTKENT-ERKAM SİTESİ");
            addRoute("ESENTEPE-48 EVLER");//Esentepe 48.62 -> Esentepe1
            addRoute("GÜLKENT-KARAÇAY");
            addRoute("İMAMHATİP-AĞDACI");
            addRoute("KANLITÜRBE-KAVALLAR.");
            addRoute("KASAPOĞLU");
            addRoute("KAYNARCA-TOKİ");
            addRoute("ORDUYERİ-CEZAEVİ");
            addRoute("ŞİREMİŞ-ÇAVUŞ KÖYÜ");
            addRoute("ÜNİVERSİTE");
            addRoute("YENİ TERMİNAL");
            addRoute("YENİMAHALLE-ÇAYIRLAR");
            //cityRepository.getById((long)74).getDistricts().get(0).setRoutes(routeRepository.findAll());
            //cityRepository.getById((long)74).getDistricts().get(0).setStations(stationRepository.findAll());
        
        Privilege erot = createPrivilegeIfNotFound("EDIT_ROTATIONS");
        Privilege esta = createPrivilegeIfNotFound("EDIT_STATIONS");
        Privilege ebus = createPrivilegeIfNotFound("EDIT_BUSSES");
        Privilege euse = createPrivilegeIfNotFound("EDIT_USERS");
        Privilege lbal = createPrivilegeIfNotFound("LOAD_BALANCE");
        Privilege lbalhım = createPrivilegeIfNotFound("LOAD_BALANCE_HIMSELF");

        List<Privilege> adminPrivileges = Arrays.asList(erot, esta,ebus,euse,lbal,lbalhım);
        List<Privilege> userPrivileges = Arrays.asList(lbalhım);
        List<Privilege> dealerPrivileges = Arrays.asList(lbal,lbalhım);
        List<Privilege> modPrivileges = Arrays.asList(erot,esta,ebus);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);
        createRoleIfNotFound("ROLE_DEALER", dealerPrivileges);
        createRoleIfNotFound("ROLE_MOD", modPrivileges);
        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
        City city = cityRepository.getById((long)74);
        District district = city.getDistricts().get(0);
        User admin = new User();
        admin.setFirstname("Ömer Faruk");
        admin.setLastname("Kayıkcı");
        admin.setEmail("omer.twd@gmail.com");
        admin.setRoles(Arrays.asList(adminRole));
        admin.setCity(city);
        admin.setDistrict(district);
        admin.setEnabled(true);
        admin.setLocation(new Location());
        admin.getLocation().setLocation(new Point<G2D>(g(0, 0), WGS84));
        admin.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(admin);
        
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        User user = new User();
        user.setFirstname("Test User");
        user.setLastname("Test");
        user.setEmail("test@gmail.com");
        user.setCity(city);
        user.setDistrict(district);
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        user.setLocation(new Location());
        user.getLocation().setLocation(new Point<G2D>(g(0, 0), WGS84));
        user.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user);
        
        
        alreadySetup = true;
    }
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/*  */
    @Data
    private static class RouteH{
        @JsonProperty("StopId") 
        public int stopId;
        @JsonProperty("StopName") 
        public String stopName;
        @JsonProperty("Sequence") 
        public int sequence;
        @JsonProperty("Latitude") 
        public String latitude;
        @JsonProperty("Longitude") 
        public String longitude;
        @JsonProperty("Direction") 
        public Object direction;
        @JsonProperty("HasBusLeaved") 
        public int hasBusLeaved;
        public RouteH(int stopId, String stopName, int sequence, String latitude, String longitude, Object direction,
                int hasBusLeaved) {
            this.stopId = stopId;
            this.stopName = stopName;
            this.sequence = sequence;
            this.latitude = latitude;
            this.longitude = longitude;
            this.direction = direction;
            this.hasBusLeaved = hasBusLeaved;
        }
        public RouteH() {
        }
    }

    @Transactional
    void addRoute(String routeName){
        Path path;
        String str = null;
        Route route = new Route();
        try {
            path = ResourceUtils.getFile("classpath:data/RouteData/"+routeName+".json").toPath();
            str = Files.readString(path);
            ObjectMapper om = new ObjectMapper();
            RouteH[] root = om.readValue(str, RouteH[].class);
            route.setRouteName(routeName);
            route.setStations(new ArrayList<Station>());
            route.setRouteLine(new ArrayList<Location>());
            int i = 1;
            City city = cityRepository.getById((long)74);
            District district = city.getDistricts().get(0);
            for (RouteH item : root) {
                Station station = stationRepository.findByStationName(item.stopName).orElseThrow(
                    () -> new ResourceNotFoundException("Station", "stationName","++"+item.stopName+"++")
                );
                route.getRouteLine().add(new Location(Double.parseDouble(item.latitude) ,Double.parseDouble(item.longitude),false,i));
                route.getStations().add(station);
                route.setCity(city);
                route.setDistrict(district);
                i++;
            }
            routeRepository.save(route);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }




    @Data
    private static class LocationH{
        public int locationID;
        public Object updatedAt;
        public double longitude;
        public double latitude;
        public boolean active;
        public LocationH(int locationID, Object updatedAt, double longitude, double latitude, boolean active) {
            this.locationID = locationID;
            this.updatedAt = updatedAt;
            this.longitude = longitude;
            this.latitude = latitude;
            this.active = active;
        }
        public LocationH() {
        }
    }
    @Data
    private static class StationH{
        public int stationID;
        public String stationName;
        public LocationH location;
        public double stationScope;
        public Object routes;
        public StationH(int stationID, String stationName, LocationH location, double stationScope, Object routes) {
            this.stationID = stationID;
            this.stationName = stationName;
            this.location = location;
            this.stationScope = stationScope;
            this.routes = routes;
        }
        public StationH() {
        }
    }
    @Transactional
    void createStations(){
        Path path;
        String str = null;
        try {
            List<Station> stations = new ArrayList<>();
            path = ResourceUtils.getFile("classpath:data/stations74.json").toPath();
            str = Files.readString(path);
            ObjectMapper om = new ObjectMapper();
            StationH[] stationH = om.readValue(str, StationH[].class);
            City city = cityRepository.findById((long)74).orElseThrow(
                () -> new ResourceNotFoundException("City", "ID", 74)
            );
            District district = city.getDistricts().get(0);
            for (StationH item : stationH) {
                Station station = new Station();
                station.setStationName(item.stationName);
                station.setLocation(new Location());
                Point<G2D> point = new Point<G2D>(g(item.location.longitude, item.location.latitude), WGS84);
                station.getLocation().setLocation(point);
                station.setStationScope((int)item.stationScope);
                station.setStationID(item.stationID);
                station.setCity(city);
                station.setDistrict(district);
                stations.add(station);
            }
            stationRepository.saveAll(stations);
        } catch (FileNotFoundException e) {
            // 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Transactional
    void createCityAndDistrict(){
        try{
            Path path = ResourceUtils.getFile("classpath:data/city-district.json").toPath();
            String str = Files.readString(path);
            ObjectMapper om = new ObjectMapper();
            City[] root = om.readValue(str, City[].class);
            cityRepository.saveAll(List.of(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        if (privilegeRepository.existsPrivilageByPrivilegeName(name)) {
            return null;
        }
        Privilege privilege = new Privilege(name);
        privilegeRepository.save(privilege);
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privileges) {

        if (roleRepository.existsRoleByRoleName(name)) {
            return null;
        }
        Role role = new Role(name,privileges);
        roleRepository.save(role);
        return role;
    }
}

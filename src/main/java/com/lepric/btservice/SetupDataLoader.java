
package com.lepric.btservice;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lepric.btservice.model.City;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.User;
import com.lepric.btservice.repository.CityRepository;
import com.lepric.btservice.repository.PrivilegeRepository;
import com.lepric.btservice.repository.RoleRepository;
import com.lepric.btservice.repository.UserRepository;

@Component
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {
    
    boolean alreadySetup = true;

    @Autowired
    private UserRepository userRepository;
 
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
        User admin = new User();
        admin.setFirstname("Ömer Faruk");
        admin.setLastname("Kayıkcı");
        admin.setEmail("omer.twd@gmail.com");
        admin.setRoles(Arrays.asList(adminRole));
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
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        user.setLocation(new Location());
        user.getLocation().setLocation(new Point<G2D>(g(0, 0), WGS84));
        user.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user);
        
        createCityAndDistrict();
        alreadySetup = true;
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

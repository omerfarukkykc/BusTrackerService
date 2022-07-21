
package com.lepric.btservice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.City;
import com.lepric.btservice.model.District;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.User;
import com.lepric.btservice.repository.PrivilegeRepository;
import com.lepric.btservice.repository.RoleRepository;
import com.lepric.btservice.service.UserService;

@Component
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {
    
    boolean alreadySetup = false;

    @Autowired
    private UserService userService;
 
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;
    
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

        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElseThrow(
            () -> new ResourceNotFoundException("Role", "Role Name", "ROLE_ADMIN")
        );
        User user = new User();
        user.setFirstname("Ömer Faruk");
        user.setLastname("Kayıkcı");
        user.setPassword("123456");
        user.setEmail("omer.twd@gmail.com");
        user.setRols(Arrays.asList(adminRole));
        user.setEnabled(true);
        userService.AddUser(user);

        
        alreadySetup = true;
    }
    void createCityAndDistrict(){
        //Todo Json okuma işlemi düzeltilecek veya alternatif yol aranacak.


        List<City> citiesList =new ArrayList<City>();
        try (Reader reader = new FileReader(ResourceUtils.getFile("classpath:data/city-district.json"))) {
            
            JSONParser alldata = new JSONParser(reader);
            JSONObject cities = (JSONObject) alldata.parse();
            JSONArray citieArray = (JSONArray)cities.get("data");
            Iterator<String> iterator = citieArray.iterator();
            while (iterator.hasNext()) {
                JSONParser citiyData = new JSONParser(iterator.next());
                JSONObject cityObject = (JSONObject) citiyData.parse();
                City city = new City();
                List<District> districtsList = new ArrayList<District>();
                city.setDistricts(districtsList);
                city.setCityName((String)cityObject.get("cityName"));
                JSONArray districtsObject = (JSONArray)cityObject.get("districts");
                Iterator<String> distiterator = districtsObject.iterator();
                while (distiterator.hasNext()) {
                    District district = new District();
                    district.setDistrictName(distiterator.next());
                    districtsList.add(district);
                }
                citiesList.add(city);
            }


        } catch (IOException | ParseException e) {
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

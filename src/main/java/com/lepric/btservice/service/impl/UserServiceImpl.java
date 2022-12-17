package com.lepric.btservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.Route;
import com.lepric.btservice.model.Station;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.AmountResponse;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;
import com.lepric.btservice.model.BalanceLog;
import com.lepric.btservice.model.Bus;
import com.lepric.btservice.model.Favorite;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.repository.BalanceLogRepository;
import com.lepric.btservice.repository.BalanceLogTypeRepository;
import com.lepric.btservice.repository.BusRepository;
import com.lepric.btservice.repository.FavoriteRepository;
import com.lepric.btservice.repository.RoleRepository;
import com.lepric.btservice.repository.RouteRepository;
import com.lepric.btservice.repository.StationRepository;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private BalanceLogRepository balanceLogRepository;
    @Autowired
    private BalanceLogTypeRepository balanceLogTypeRepository;

    

    // Add New User
    @Override
    public User AddUser(User user) {
        user.setLocation(new Location());
        user.getLocation().setLocation(new Point<G2D>(g(0, 0), WGS84));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleRepository.findByRoleName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public User UpdateUser(User user, long id) {
        User dbuser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", id));
        if (user.getFirstname() != null)
            dbuser.setFirstname(user.getFirstname());

        if (user.getLastname() != null)
            dbuser.setLastname(user.getLastname());

        if (user.getEmail() != null)
            dbuser.setEmail(user.getEmail());

        if (user.getPassword() != null)
            dbuser.setPassword(user.getPassword());

        return userRepository.save(dbuser);

    }

    @Override
    public User GetUser(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", id));
        return user;
    }

    @Override
    public User GetUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));

    }

    @Override
    public List<User> GetUsers() {

        return userRepository.findAll();
    }

    @Override
    public boolean DeleteUser(long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", id));
        userRepository.deleteById(id);
        return true;
    }

    // Get User Rols
    @Override
    public List<Role> GetUserRols(long userID) {
        User dbUser = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        return dbUser.getRoles();
    }

    @Override
    public boolean ChangePassword(long userID, UpdatePasswordModelHelper upmh) {
        User dbUser = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        if (!upmh.getOldPassword().equals(dbUser.getPassword())) {
            return false;
        }
        if (!upmh.getNewPassword().equals(upmh.getNewPasswordAgain())) {
            return false;
        }
        dbUser.setPassword(upmh.getNewPassword());
        userRepository.save(dbUser);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", username));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> getAuthorities(List<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(List<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> List = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getRoleName());
            List.addAll(role.getPrivileges());
        }
        for (Privilege item : List) {
            privileges.add(item.getPrivilegeName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    @Transactional
    public AmountResponse getPayment(String cardID,long busID) {
        User user = userRepository.findByCardID(cardID).orElseThrow(
                () -> new ResourceNotFoundException("User", "cardID", cardID));

        Bus bus = busRepository.findById(busID).orElseThrow(
                () -> new ResourceNotFoundException("Bus", "ID", busID));
        Route route = bus.getRoute();
        
        Station currentStation  = bus.getCurrentStation();
        int currentStationIndex = route.getStations().indexOf(currentStation)+1;
        int totalStationSize = route.getStations().size();
        int stationLeft = totalStationSize - currentStationIndex;
        double price = 0.0;
        if(route.getFee().getStationFeeValue()*stationLeft<route.getFee().getFullFeeValue()){
            price = route.getFee().getStationFeeValue()*stationLeft;
        }else{
            price = route.getFee().getFullFeeValue();
        }
        if(user.getBalance()>=price) {
            user.setBalance(user.getBalance()-price);
            user.setStartStation(bus.getCurrentStation());
            user.setActiveRoute(route);
            userRepository.save(user);
            BalanceLog balanceLog = new BalanceLog();
            balanceLog.setLogAmount(price);
            balanceLog.setLogType(balanceLogTypeRepository.findAll().stream().filter(x->x.getLogTypeName().equals("Pay")).findFirst().get());
            balanceLog.setUser(user);
            balanceLogRepository.save(balanceLog);
            return new AmountResponse(true,user.getBalance(),price);
        }else{
            return new AmountResponse(false,user.getBalance(),price);
        }   
    }

    @Override
    @Transactional
    public double loadBalance(String cardID,double amount) {
        User user = userRepository.findByCardID(cardID).orElseThrow(
                () -> new ResourceNotFoundException("User", "cardID", cardID));
        user.setBalance(user.getBalance()+amount);
        userRepository.save(user);
        BalanceLog balanceLog = new BalanceLog();
        balanceLog.setLogAmount(amount);
        balanceLog.setLogType(balanceLogTypeRepository.findAll().stream().filter(x->x.getLogTypeName().equals("Load")).findFirst().get());
        balanceLog.setUser(user);
        balanceLogRepository.save(balanceLog);
        return user.getBalance();
    }

    @Override
    public double getBalance(String cardID) {
        User user = userRepository.findByCardID(cardID).orElseThrow(
                () -> new ResourceNotFoundException("User", "cardID", cardID));
        return user.getBalance();
    }

    @Override
    public double getBalance(long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        return user.getBalance();
    }

    @Override
    @Transactional
    public double loadBalance(long userID, double amount) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        user.setBalance(user.getBalance()+amount);
        userRepository.save(user);
        BalanceLog balanceLog = new BalanceLog();
        balanceLog.setLogAmount(amount);
        balanceLog.setLogType(balanceLogTypeRepository.findAll().stream().filter(x->x.getLogTypeName().equals("Load")).findFirst().get());
        balanceLog.setUser(user);
        balanceLogRepository.save(balanceLog);
        return user.getBalance();
    }

    @Override
    @Transactional
    public Double getRefund(String cardID, long stationID) {
        User user = userRepository.findByCardID(cardID).orElseThrow(
                () -> new ResourceNotFoundException("User", "cardID", cardID));
        if(user.getActiveRoute()==null||user.getStartStation()==null) {
            return 0.0;
        }
        
        Station endStation = stationRepository.findById(stationID).orElseThrow(
                () -> new ResourceNotFoundException("Staton", "ID", stationID));
        int count = 0;
        boolean countFlag = false;
        for (Station station : user.getActiveRoute().getStations()) {
            if(station.getStationID()==user.getStartStation().getStationID()) {
                countFlag = true;
            }else if(station.getStationID()==endStation.getStationID()) {
                countFlag = false;
            }
            if(countFlag){
                count++;
            }
        }
        if(count*user.getActiveRoute().getFee().getStationFeeValue()>user.getActiveRoute().getFee().getFullFeeValue()) {
            return 0.0;
        }else{
            double refund = user.getActiveRoute().getFee().getFullFeeValue()-count*user.getActiveRoute().getFee().getStationFeeValue();
            user.setBalance(user.getBalance()+refund);
            user.setActiveRoute(null);
            user.setStartStation(null);
            userRepository.save(user);
            BalanceLog balanceLog = new BalanceLog();
            balanceLog.setLogAmount(refund);
            balanceLog.setLogType(balanceLogTypeRepository.findAll().stream().filter(x->x.getLogTypeName().equals("Refund")).findFirst().get());
            balanceLog.setUser(user);
            balanceLogRepository.save(balanceLog);
            return refund;
        }//Todo : add response for this and add newbalance and refund amount to response
    }

    @Override
    @Transactional
    public Double getRefund(long userID, long stationID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "userID", userID));
        if(user.getActiveRoute()==null||user.getStartStation()==null) {
            return 0.0;
        }
        
        Station endStation = stationRepository.findById(stationID).orElseThrow(
                () -> new ResourceNotFoundException("Staton", "ID", stationID));
        int count = 0;
        boolean countFlag = false;
        for (Station station : user.getActiveRoute().getStations()) {
            if(station.getStationID()==user.getStartStation().getStationID()) {
                countFlag = true;
            }else if(station.getStationID()==endStation.getStationID()) {
                countFlag = false;
            }
            if(countFlag){
                count++;
            }
        }
        if(count*user.getActiveRoute().getFee().getStationFeeValue()>user.getActiveRoute().getFee().getFullFeeValue()) {
            return 0.0;
        }else{


            double refund = user.getActiveRoute().getFee().getFullFeeValue()-count*user.getActiveRoute().getFee().getStationFeeValue();
            user.setBalance(user.getBalance()+refund);
            user.setActiveRoute(null);
            user.setStartStation(null);
            userRepository.save(user);
            BalanceLog balanceLog = new BalanceLog();
            balanceLog.setLogAmount(refund);
            balanceLog.setLogType(balanceLogTypeRepository.findAll().stream().filter(x->x.getLogTypeName().equals("Refund")).findFirst().get());
            balanceLog.setUser(user);
            balanceLogRepository.save(balanceLog);
            return refund;
        }//Todo : add response for this and add newbalance and refund amount to response
        
    }

    @Override
    public List<Favorite> GetFavorites(long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        return user.getFavorites();
    }

    

    @Override
    public Favorite AddFavoriteRoute(long userID, long routeID) {
        User user = userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID));
        Route route = routeRepository.findById(routeID).orElseThrow(
                () -> new ResourceNotFoundException("Route", "ID", routeID));
        Favorite favorite = new Favorite();
        favorite.setRoute(route);
        favorite.setUser(user);
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite AddFavoriteStation(long userID, long stationID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        Station station = stationRepository.findById(stationID).orElseThrow(
                () -> new ResourceNotFoundException("Station", "ID", stationID));
        Favorite favorite = new Favorite();
        favorite.setStation(station);
        favorite.setUser(user);
        return favoriteRepository.save(favorite);
    }

    @Override
    public boolean DeleteFavorite(long userID, long favoriteID) {
        try{
            favoriteRepository.deleteById(null);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<BalanceLog> GetBalanceLogs(long userID) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User", "ID", userID));
        return user.getBalanceLogs();
    }
}

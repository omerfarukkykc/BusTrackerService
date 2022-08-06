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
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.repository.RoleRepository;
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

}

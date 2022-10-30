package com.lepric.btservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lepric.btservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByCardID(String cardID);

}

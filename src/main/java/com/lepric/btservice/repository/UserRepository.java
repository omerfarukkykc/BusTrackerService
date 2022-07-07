package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lepric.btservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


}

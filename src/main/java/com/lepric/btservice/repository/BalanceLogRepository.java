package com.lepric.btservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lepric.btservice.model.BalanceLog;

public interface BalanceLogRepository extends JpaRepository<BalanceLog, Long> {


}

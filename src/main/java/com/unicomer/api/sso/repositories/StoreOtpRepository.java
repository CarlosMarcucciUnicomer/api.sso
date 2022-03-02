package com.unicomer.api.sso.repositories;

import com.unicomer.api.sso.models.StoreOtp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOtpRepository extends JpaRepository<StoreOtp, Long> {
    public abstract StoreOtp findFirstByEmployeeCodeOrderByOtpRequestedTimeDesc(String employeeCode);
}
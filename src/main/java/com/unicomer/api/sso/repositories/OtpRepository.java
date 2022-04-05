package com.unicomer.api.sso.repositories;

import com.unicomer.api.sso.models.Otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    public abstract Otp findFirstByEmployeeCodeAndIdAppOrderByOtpRequestedTimeDesc(String employeeCode, Integer idApp);
}
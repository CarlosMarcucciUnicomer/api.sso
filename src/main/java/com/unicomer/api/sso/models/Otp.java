package com.unicomer.api.sso.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "OTPS")
public class Otp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long otpId;

  private String employeeCode;
  private Integer countryCode;
  private Long phone;
  private Date otpRequestedTime;
  private Integer idApp;

  @JsonIgnore
  @JsonSetter
  private String oneTimePassword;

  public Long getOtpId() {
    return this.otpId;
  }

  public void setOtpId(Long otpId) {
    this.otpId = otpId;
  }

  public String getEmployeeCode() {
    return this.employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public Integer getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(Integer countryCode) {
    this.countryCode = countryCode;
  }

  public Long getPhone() {
    return this.phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public String getOneTimePassword() {
    return this.oneTimePassword;
  }

  public void setOneTimePassword(String oneTimePassword) {
    this.oneTimePassword = oneTimePassword;
  }

  public Date getOtpRequestedTime() {
    return this.otpRequestedTime;
  }

  public void setOtpRequestedTime(Date otpRequestedTime) {
    this.otpRequestedTime = otpRequestedTime;
  }

  public Integer getIdApp() {
    return this.idApp;
  }

  public void setIdApp(Integer idApp) {
    this.idApp = idApp;
  }

}
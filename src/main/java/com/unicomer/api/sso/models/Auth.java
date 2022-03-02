package com.unicomer.api.sso.models;

public class Auth {
  private String username;
  private String message;
  private Boolean isAuthenticated;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean isIsAuthenticated() {
    return this.isAuthenticated;
  }

  public Boolean getIsAuthenticated() {
    return this.isAuthenticated;
  }

  public void setIsAuthenticated(Boolean isAuthenticated) {
    this.isAuthenticated = isAuthenticated;
  }

}

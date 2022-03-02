package com.unicomer.api.sso.controllers;

import com.unicomer.api.sso.general.configuration.DataSourceConfig;
import com.unicomer.api.sso.models.Auth;
import com.unicomer.api.sso.models.dto.AuthBodyDto;
import com.unicomer.api.sso.services.LdapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("/api/v1/ldap")
public class LdapController {
  @Autowired
  LdapService ldapService;

  @Autowired
  DataSourceConfig dataSource;

  @PostMapping("/auth")
  @ResponseStatus(HttpStatus.OK)
  public Auth authorization(@RequestBody AuthBodyDto body) {
    try {
      ldapService.setDataSource(dataSource.getDataSource());
      Auth auth = ldapService.getAuthorization(body.getUsername(), body.getPassword());
      return auth;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}

package com.unicomer.api.sso;

import javax.sql.DataSource;

import com.unicomer.api.sso.general.configuration.DataSourceConfig;
import com.unicomer.api.sso.services.LdapService;

import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication
// @RestController
// public class Application {

// 	public static void main(String[] args) {
// 		SpringApplication.run(Application.class, args);
// 	}

// 	@GetMapping("/hello")
// 	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
// 		return String.format("Hello %s!", name);
// 	}
// }

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
  @Autowired
  LdapService ldapService;

  @Autowired
  DataSourceConfig dataSource;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }
}
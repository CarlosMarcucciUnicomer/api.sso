package com.unicomer.api.sso.services;

import java.math.BigDecimal;

import javax.sql.DataSource;

import com.unicomer.api.sso.models.Auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class LdapService {
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  public Auth getAuthorization(String username, String password) {
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("unicomer_ldap_auth_number");

    SqlParameterSource in = new MapSqlParameterSource()
        .addValue("p_username", username)
        .addValue("p_password", password);
    BigDecimal authenticated = jdbcCall.executeFunction(BigDecimal.class, in);
    Boolean isAuthenticated = authenticated.equals(new BigDecimal(1));

    Auth auth = new Auth();
    auth.setUsername(username);
    auth.setIsAuthenticated(isAuthenticated);

    if (auth.getIsAuthenticated()) {
      auth.setMessage("Autenticación exitosa");
    } else {
      auth.setMessage("Usuario y/o contraseña incorrecto");
    }
    return auth;
  }
}

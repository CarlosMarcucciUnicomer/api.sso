package com.unicomer.api.sso.general.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@//10.2.4.146:1521/ORCLDB");
        dataSourceBuilder.username("DEV");
        dataSourceBuilder.password("Un1c0m3r/dEv");
        return dataSourceBuilder.build();
    }
}
package com.nutn.utm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@PropertySource(value = {"classpath:datasource.properties"})
@Configuration
public class JpaConfig {


    @Value("${db_driver_class}")
    private String driverClass;

    @Value("${db_url}")
    private String url;

    @Value("${db_username}")
    private String username;

    @Value("${db_password}")
    private String password;

    @Bean
    @Profile("dev")
    @ConditionalOnMissingBean
    public DataSource embeddedDataBase() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource mysql() {
        return DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}


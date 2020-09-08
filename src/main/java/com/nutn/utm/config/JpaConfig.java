package com.nutn.utm.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Configuration
public class JpaConfig {
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
        ResourceBundle properties = ResourceBundle.getBundle("datasource");
        return DataSourceBuilder.create()
                .driverClassName(properties.getString("driverClass"))
                .url(properties.getString("url"))
                .username(properties.getString("username"))
                .password(properties.getString("password"))
                .build();
    }
}


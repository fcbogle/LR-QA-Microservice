package org.frank.bogle.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Configuration
@Profile({"jpa_development"})
@Import({DevelopmentJpaConfig.class})
@PropertySource("classpath:jpadev.properties")
public class DevelopmentJpaDataSource {

    private Logger logger = LoggerFactory.getLogger(DevelopmentJpaDataSource.class);

    @Value("${jpa.dev.driver}")
    private String driver;

    @Value("${jpa.dev.url}")
    private String url;

    @Value("${jpa.dev.username}")
    private String username;

    @Value("${jpa.dev.password}")
    private String password;

    @Value("${jpa.dev.message}")
    private String message;



    @Primary
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        /**
         * Read these values in from properties or environment
         */

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        logger.info(message);

        return dataSource;

    }
}

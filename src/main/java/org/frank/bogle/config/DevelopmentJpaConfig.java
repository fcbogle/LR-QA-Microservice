package org.frank.bogle.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Configuration
@Profile({"jpa_development"})
public class DevelopmentJpaConfig {

    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(DevelopmentJpaConfig.class);


    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean emfb =
                new LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan("person, hotel");
        emfb.setJpaProperties(props);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setDataSource(dataSource);

        logger.info("AUTOWIRED DATASOURCE CONNECTION OBJECT :::: " + dataSource.toString());

        return emfb;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new JpaTransactionManager();
    }

}


package org.frank.bogle.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.time.LocalDateTime;

import static java.util.Collections.singletonList;

/**
 * Created by frankbogle on 20/12/2016.
 */

@Profile({"development"})
@PropertySource("classpath:mongodev.properties")
@Configuration
public class DevelopmentMongoConfig extends AbstractMongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DevelopmentMongoConfig.class);

    @Value("${mongo.dev.host}")
    private String host;

    @Value("${mongo.dev.port}")
    private Integer port;

    @Value("${mongo.dev.database}")
    private String database;

    @Value("${mongo.dev.username}")
    private String username;

    @Value("${mongo.dev.password}")
    private String password;

    @Override
    public String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        logger.info("DevelopmentMongoConfig method mongo() invoked for database Stormpath: " + LocalDateTime.now());
        return new MongoClient(
                singletonList(new ServerAddress(host, port)),
                singletonList(MongoCredential.createCredential(username,
                        database, password.toCharArray()))
        );

    }

}

package org.frank.bogle.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by frankbogle on 13/01/2017.
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return builder().build();
    }

    @Bean
    public RestTemplateBuilder builder() {
        return new RestTemplateBuilder();
    }
}

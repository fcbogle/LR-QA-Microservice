package org.frank.bogle.config;

import com.stormpath.sdk.application.Application;
import com.stormpath.spring.security.provider.StormpathAuthenticationProvider;
import org.frank.bogle.filter.PreAuthenticationFilter;
import org.frank.bogle.handler.StormpathHandler;
import org.frank.bogle.service.LRPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class StormpathConfig {

    @Autowired
    private Application application;


    private LRPersonService lrPersonService;

    @Autowired
    public StormpathConfig(LRPersonService lrPersonService) {
        this.lrPersonService = lrPersonService;
    }


    @Bean
    public StormpathAuthenticationProvider stormpathAuthenticationProvider(Application application) {
        StormpathAuthenticationProvider provider = new StormpathAuthenticationProvider(application);
        return provider;
    }

    @Bean
    public StormpathHandler stormpathRequestEventListener() {
        return new StormpathHandler(lrPersonService);
    }

}
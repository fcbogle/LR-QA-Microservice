package org.frank.bogle.config;

import com.stormpath.spring.security.provider.StormpathAuthenticationProvider;
import org.frank.bogle.filter.PreAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by frankbogle on 22/12/2016.
 */
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class FilterConfig {

    private StormpathAuthenticationProvider stormpathAuthenticationProvider;

    @Autowired
    public FilterConfig(StormpathAuthenticationProvider stormpathAuthenticationProvider) {
        this.stormpathAuthenticationProvider = stormpathAuthenticationProvider;
    }

    /**
    @Profile({"development"})
    @Bean
    public PreAuthenticationFilter preAuthenticationFilter() {
        return new PreAuthenticationFilter(stormpathAuthenticationProvider);
    }
    */
}

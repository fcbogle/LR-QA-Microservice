package org.frank.bogle.security;

import com.stormpath.spring.security.provider.StormpathAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StormpathAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(stormpath())
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(provider)
                .getDefaultUserDetailsService();
    }

}
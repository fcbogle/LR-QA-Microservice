package org.frank.bogle.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by frankbogle on 22/12/2016.
 */
@PropertySource("classpath:stmpathgroup.properties")
@Component
public class Roles {

    public final GrantedAuthority ADMIN;
    public final GrantedAuthority USER;
    public final GrantedAuthority STEPUP_USER;

    @Autowired
    public Roles(Environment env) {

        ADMIN = new
                SimpleGrantedAuthority(env.getProperty("stormpath.authorized.group.admin"));
        USER = new
                SimpleGrantedAuthority(env.getProperty("stormpath.authorized.group.user"));
        STEPUP_USER = new
                SimpleGrantedAuthority(env.getProperty("stormpath.authorized.group.bos_user"));

    }
}
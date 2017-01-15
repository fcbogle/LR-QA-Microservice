package org.frank.bogle.controller;

import org.frank.bogle.groups.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by frankbogle on 13/01/2017.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Roles roles;

    @RequestMapping(value = "/postLogin", method = RequestMethod.GET)
    public ModelAndView postLogin(HttpServletRequest request, Model model) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        logger.info("Principal User :::: " + principal.toString());

        Collection<? extends GrantedAuthority> grantedAuthorities =
                authentication.getAuthorities();

        List<String> groups = new ArrayList<>();

        for (GrantedAuthority g : grantedAuthorities) {
            logger.info("Granted Authority added to group :::: " + g.getAuthority());
            groups.add(g.getAuthority());
        }

        logger.info("GrantedAuthorities Collection :::: " + grantedAuthorities.toString());

        if (grantedAuthorities.contains(roles.ADMIN)) {
            logger.info("LoginController Returning ADMIN Home Page");
            return new ModelAndView("adminhome");

        } else if (grantedAuthorities.contains(roles.USER)) {
            logger.info("LoginController Returning USER Home Page");
            return new ModelAndView("userhome");
        }

        logger.info("LoginController Returning APP Home Page");
        return new ModelAndView("apphome");

    }
}

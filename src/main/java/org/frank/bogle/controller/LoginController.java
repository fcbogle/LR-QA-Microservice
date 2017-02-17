package org.frank.bogle.controller;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.servlet.account.AccountResolver;
import org.frank.bogle.groups.Roles;
import org.frank.bogle.model.LRPerson;
import org.frank.bogle.service.LRPersonService;
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
@SuppressWarnings("SpringJavaAutowiringInspection")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Roles roles;

    @Autowired
    private LRPersonService lrPersonService;

    @Autowired
    private AccountResolver accountResolver;

    private String email;

    @RequestMapping(value = "/postLogin", method = RequestMethod.GET)
    public ModelAndView postLogin(HttpServletRequest request, Model model) {

        if(this.accountResolver.hasAccount(request)){
            Account account = accountResolver.getRequiredAccount(request);
            email = account.getEmail();
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        logger.info("Email of Authenticated User :::: " + email);

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
            LRPerson person = this.lrPersonService.findLRPersonByEmail(email);
            return new ModelAndView("adminhome")
                    .addObject("person", person);

        } else if (grantedAuthorities.contains(roles.USER)) {
            logger.info("LoginController Returning USER Home Page");
            return new ModelAndView("userhome");
        }

        logger.info("LoginController Returning APP Home Page");
        return new ModelAndView("apphome");

    }
}

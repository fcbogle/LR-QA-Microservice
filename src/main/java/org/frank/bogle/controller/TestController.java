package org.frank.bogle.controller;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.servlet.account.AccountResolver;
import org.frank.bogle.service.LRPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by frankbogle on 22/12/2016.
 */
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private AccountResolver accountResolver;

    @Autowired
    private LRPersonService personService;


    @RequestMapping("/frank")
    @PreAuthorize("hasAuthority(@roles.ADMIN)")
    public String getFrank(HttpServletRequest req) throws Exception {
        String frank;
        GroupList groups;
        String principal = personService.getAccountPrincipal();
        if(accountResolver.hasAccount(req)) {
            Account account = accountResolver.getRequiredAccount(req);
            frank = account.getFullName();
            groups = account.getGroups();
            logger.info("Principal from TestController is: " + principal);
            logger.info("Username from Frank " +
                    "is :::: " + account.getUsername());
            try {
                for (Group g : groups) {
                    logger.info("Username: " + frank + " is a member of ::: " + g.getName());
                    logger.info("Group: " + g.getName() + " has the following description ::: " + g.getDescription());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Username from Frank does not exist");
            frank = "Not Found";
        }

        return "hello-frank";
    }
}

package org.frank.bogle.handler;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.servlet.account.event.RegisteredAccountRequestEvent;
import com.stormpath.sdk.servlet.authc.LogoutRequestEvent;
import com.stormpath.sdk.servlet.authc.SuccessfulAuthenticationRequestEvent;
import com.stormpath.sdk.servlet.event.RequestEventListenerAdapter;
import org.frank.bogle.model.LRPerson;
import org.frank.bogle.service.LRPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by frankbogle on 20/12/2016.
 */
public class StormpathHandler extends RequestEventListenerAdapter {

    private LRPersonService lrPersonService;
    private final static Logger logger = LoggerFactory.getLogger(StormpathHandler.class);

    @Autowired
    public StormpathHandler(LRPersonService lrPersonService) {
        this.lrPersonService = lrPersonService;
    }

    @Override
    public void on(RegisteredAccountRequestEvent event) {

        Account account = event.getAccount();
        LRPerson person = new LRPerson();
        try {
            person.setEmail(account.getEmail());
            person.setFirstName(account.getGivenName());
            person.setLastName(account.getSurname());

        } catch (Exception e) {
            e.printStackTrace();
        }

        lrPersonService.saveLrPerson(person);

        logger.info("PostPlatformHandler invoked Registration for this new Account email: " + account.getEmail());
    }

    @Override
    public void on(SuccessfulAuthenticationRequestEvent event) {

        Account account = event.getAuthenticationResult().getAccount();
        logger.info("PostPlatformHandler invoked Authentication for this new Account email: " + account.getEmail());
    }

    @Override
    public void on(LogoutRequestEvent event) {

        Account account = event.getAccount();
        logger.info("PostPlatformHandler invoked Logout for this new Account email: " + account.getEmail());
    }

}

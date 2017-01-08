package org.frank.bogle.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by frankbogle on 08/01/2017.
 */
@Aspect
public class StormpathMethodMonitor {

    private Logger logger = LoggerFactory.getLogger(StormpathMethodMonitor.class);

    @Before("org.frank.bogle.config.AspectConfig.stormpathRegisterMonitor()")
    public void monitoryStormpathRegistration() {
        logger.info("Stormpath Registration Bean About To Be Invoked :::: AOP Operational");
    }
}

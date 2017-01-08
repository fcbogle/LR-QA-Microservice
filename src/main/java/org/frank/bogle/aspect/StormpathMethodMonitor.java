package org.frank.bogle.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by frankbogle on 08/01/2017.
 */
@Aspect
@Component
public class StormpathMethodMonitor {

    private Logger logger = LoggerFactory.getLogger(StormpathMethodMonitor.class);

    @Before("org.frank.bogle.config.AspectConfig.stormpathRegisterMonitor()")
    public void namedPointcutMonitor() {
        logger.info("StormpathMethodMonitor namedPointcutMonitor() Advice Method Invoked ::: AOP");
    }

    @Before("execution(void org.frank.bogle.service.LRPersonService.save*(*))")
    public void notNamedPointcutMonitor(JoinPoint jp) {
        logger.info("Class of advised Object is: " + jp.getClass());
        logger.info("Method used on advised class is: " + jp.getSignature());
        logger.info("StormpathMethodMonitor notNamedPointcutMonitor() Advice Method Invoked ::: AOP");
    }
}

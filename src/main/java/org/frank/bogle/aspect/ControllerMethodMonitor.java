package org.frank.bogle.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by frankbogle on 10/02/2017.
 */
@Aspect
@Component
public class ControllerMethodMonitor {

    private Logger logger = LoggerFactory.getLogger(ControllerMethodMonitor.class);

    @Before("org.frank.bogle.config.AspectConfig.controllerAdviceMonitor()")
    public void namedPointcutMonitor() {
        logger.info("StormpathMethodMonitor namedPointcutMonitor() Advice Method Invoked ::: AOP");

    }

}

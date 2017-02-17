package org.frank.bogle.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by frankbogle on 07/01/2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Pointcut("execution(void org.frank.bogle.service.LRPersonService.save*(*))")
    public void stormpathRegisterMonitor(){}

    @Pointcut("execution(* org.frank.bogle.controller.ErrorController.controller*(..))")
    public void controllerAdviceMonitor(){}
}

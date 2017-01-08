package org.frank.bogle.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by frankbogle on 07/01/2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Pointcut("execution(void org.frank.bogle.config.StormpathConfig.stormpathRequestEventListener())")
    public void stormpathRegisterMonitor(){}
}

package org.frank.bogle.config;

import org.frank.bogle.filter.PreAuthenticationFilter;
import org.frank.bogle.service.LRPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by frankbogle on 23/12/2016.
 */
@Component
@Profile({"development"})
public class ContextConfig {

    private ApplicationContext context;
    private Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    @Autowired
    public ContextConfig(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void getBeanDetails() {
        PreAuthenticationFilter filter = (PreAuthenticationFilter) context.getBean("preAuthenticationFilter");
        LRPersonService service = (LRPersonService) context.getBean("LRPersonService");
        String filterClassName = filter.getClass().getName();
        String serviceClassName = service.getClass().getName();
        logger.info("ContextConfig Bean Details :::: PreAuthenticationFilter FilterClassName: " + filterClassName);
        logger.info("ContextConfig Bean Details :::: LRPersonService FilterClassName: " + serviceClassName);
        //System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
    }

}

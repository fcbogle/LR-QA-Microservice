package org.frank.bogle.config;

import org.frank.bogle.filter.PreAuthenticationFilter;
import org.frank.bogle.model.LRPerson;
import org.frank.bogle.rest.TestRestApi;
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
    private LRPersonService lrPersonService;
    private TestRestApi testRestApi;

    @Autowired
    public ContextConfig(ApplicationContext context,
                         LRPersonService lrPersonService,
                         TestRestApi testRestApi) {
        this.context = context;
        this.lrPersonService = lrPersonService;
        this.testRestApi = testRestApi;
    }

    @PostConstruct
    public void getBeanDetails() {
        //PreAuthenticationFilter filter = (PreAuthenticationFilter) context.getBean("preAuthenticationFilter");
        LRPersonService service = (LRPersonService) context.getBean("LRPersonService");
        //String filterClassName = filter.getClass().getName();
        String serviceClassName = service.getClass().getName();
        //logger.info("ContextConfig Bean Details :::: PreAuthenticationFilter FilterClassName: " + filterClassName);
        String[] allBeans = context.getBeanDefinitionNames();
        logger.info("ContextConfig All Bean Names :::: " + Arrays.asList(allBeans).toString());
        //System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
    }

    @PostConstruct
    public void createSamplePerson() {

        logger.info("INFO :::: ContextConfig Creating Sample Person");

        this.lrPersonService.deleteAllPersons();

        LRPerson person;

        try {
            person = new LRPerson();
            person.setFirstName("Test First Name");
            person.setLastName("Test Last Name");
            person.setEmail("test@test.com");
            person.setLrBusiness("Marine and Offshore");
            person.setLrProject("Test BOS");
            person.setUserName("tester");

            this.lrPersonService.saveLrPerson(person);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            person = new LRPerson();
            person.setFirstName("Test Second First Name");
            person.setLastName("Test Second Last Name");
            person.setEmail("test@test.com");
            person.setLrBusiness("Marine and Offshore");
            person.setLrProject("Test BOS");
            person.setUserName("tester");

            this.lrPersonService.saveLrPerson(person);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}

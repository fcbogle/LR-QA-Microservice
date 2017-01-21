package org.frank.bogle.controller;

import org.frank.bogle.model.Person;
import org.frank.bogle.rest.TestRestApi;
import org.frank.bogle.utilities.TestRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 19/01/2017.
 */
@Controller
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);
    private TestRestUtil testRestUtil;
    private TestRestApi testRestApi;

    @Autowired
    public PersonController(TestRestUtil testRestUtil, TestRestApi testRestApi) {
        this.testRestUtil = testRestUtil;
        this.testRestApi = testRestApi;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ModelAndView getSinglePerson(@PathVariable String id) {
        logger.info("RegisteredAdminController method getAdminPerson invoked: " + LocalDateTime.now());
        final Person person = this.testRestApi.singlePerson(id);
        return new ModelAndView("singleperson")
                .addObject("person", person)
                .addObject("links", Arrays.asList(
                        linkTo(methodOn(PersonController.class)
                                .getAllPeople())
                                .withRel("All People")
                ));
    }

    @RequestMapping(value = "/allpeople", method = RequestMethod.GET)
    public ModelAndView getAllPeople(){
        logger.info("RegisteredAdminController method getAdminAllPeople invoked: " + LocalDateTime.now());
        return new ModelAndView("allpeople")
                .addObject("people",
                        this.testRestUtil.addPersonLinkToList());
    }




}

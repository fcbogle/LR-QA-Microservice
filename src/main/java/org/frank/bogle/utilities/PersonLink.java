package org.frank.bogle.utilities;

import org.frank.bogle.controller.PersonController;
import org.frank.bogle.model.Person;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by frankbogle on 19/01/2017.
 */
public class PersonLink {

    private final Person person;
    private final Link link;

    public PersonLink(Person person){
        this.person = person;
        this.link = linkTo(methodOn(PersonController.class)
                .getSinglePerson(person.getId()))
                .withRel(person.getFirstName() + " " + person.getLastName());

    }

    public Person getPerson(){
        return person;
    }

    public Link getLink(){
        return link;
    }
}

package org.frank.bogle.rest;

import org.frank.bogle.model.LRPerson;
import org.frank.bogle.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by frankbogle on 13/01/2017.
 */
@Service
public class TestRestApi {

    private RestTemplate restTemplate;

    @Autowired
    public TestRestApi(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Person[] apiAllPeople() {
        String uri = "http://yapreunion30.cfapps.io/getrestpersons";
        ResponseEntity<Person[]> response = restTemplate.getForEntity(uri, Person[].class);
        Person[] persons = response.getBody();
        return persons;
    }

    public ResponseEntity<Person> registerRestApi() {
        String uri = "http://yapreunion30.cfapps.io/registernew";
        Person person = new Person();
        person.setEmail("Rest2 Test2 Email");
        person.setFirstName("Rest2 Test2 Firstname");
        person.setLastName("Rest2 Test2 Lastname");
        person.setContactMobile("Rest2 Test2 Mobile");

        ResponseEntity<Person> response = restTemplate.postForEntity(uri, person, Person.class);

        return response;
    }

    public Person singlePerson(String id) {
        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("yapreunion30.cfapps.io")
                .path("/person/{id}")
                .build()
                .expand(id)
                .toUriString();

        HttpEntity<Person> entity = restTemplate.getForEntity(uri, Person.class);
        Person person = entity.getBody();

        return person;
    }

}

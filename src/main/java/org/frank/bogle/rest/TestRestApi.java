package org.frank.bogle.rest;

import org.frank.bogle.model.LRPerson;
import org.frank.bogle.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        HttpHeaders header = response.getHeaders();
        return persons;
    }

    public ResponseEntity<String> registerRestApi() {
        String uri = "http://yapreunion30.cfapps.io/register";
        Person person = new Person();
        person.setEmail("Rest Test Email");
        person.setFirstName("Rest Test Firstname");
        person.setLastName("Rest Test Lastname");
        person.setContactMobile("Rest Test Mobile");

        ResponseEntity<String> response = restTemplate.postForEntity(uri, person, String.class);

        return response;

    }



}

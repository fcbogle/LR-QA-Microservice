package test.frank.bogle.restapi;

import org.frank.bogle.config.RestConfig;
import org.frank.bogle.model.Person;
import org.frank.bogle.rest.TestRestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by frankbogle on 14/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RestConfig.class})
public final class RestTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testMultipleGetPersons() {
        String uri = "http://yapreunion30.cfapps.io/getrestpersons";
        ResponseEntity<Person[]> response = restTemplate.getForEntity(uri, Person[].class);
        Person[] persons = response.getBody();
        assertNotNull(persons);
    }

    @Test
    public void testMultipleStatusCode() {
        String uri = "http://yapreunion30.cfapps.io/getrestpersons";
        ResponseEntity<Person[]> response = restTemplate.getForEntity(uri, Person[].class);
        HttpStatus status = response.getStatusCode();
        assertEquals(HttpStatus.FOUND, status);
    }

}

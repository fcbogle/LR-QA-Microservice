package test.frank.bogle.restapi;

import org.frank.bogle.config.RestConfig;
import org.frank.bogle.model.Person;
import org.frank.bogle.rest.TestRestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by frankbogle on 14/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestRestApi.class, RestConfig.class})
public class RestApiTest {

    @Autowired
    private TestRestApi testRestApi;

    @Test
    public void testAllPersonsRetrieve() {
        Person[] persons = this.testRestApi.apiAllPeople();
        assertNotNull(persons);
    }

    @Test
    public void testRestApiRegister() {
        ResponseEntity<Person> response = this.testRestApi.registerRestApi();
        Person person = response.getBody();
        assertNotNull(person);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testRestSinglePerson() {
        Person person = this.testRestApi.singlePerson("5799f02c3b7a1f0f276f6da7");
        assertNotNull(person);
    }

    @Test
    public void testPersonEdit() {
        final Person person = new Person();
        person.setFirstName("Francis Edit");
        person.setLastName("Bogle Edit");
        person.setContactMobile("07713 752 791");
        person.setEmail("fcbogle@gmail.com");

        ResponseEntity<Person> response = this.testRestApi.postUpdatedPerson("588d09c8f4990e000e3e7c28", person);
        assertNotNull(person);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }


}
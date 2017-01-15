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
        ResponseEntity<String> response = this.testRestApi.registerRestApi();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


}
package org.frank.bogle.utilities;

import org.frank.bogle.model.Person;
import org.frank.bogle.rest.TestRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by frankbogle on 19/01/2017.
 */
@Component
public class TestRestUtil implements PersonUtil {

    private Person[] restPersons = null;
    private List<Person> utilPersons = null;
    private TestRestApi testRestApi;

    @Autowired
    public TestRestUtil(TestRestApi testRestApi){
        this.testRestApi = testRestApi;
    }

    /**@PostConstruct**/
    public void init() {
        Person[] fromApi = this.testRestApi.apiAllPeople();
        this.setRestPersons(fromApi);
    }

    public List<Person> createListFromObject() {
        Person[] persons = this.getRestPersons();
        List<Person> list = new ArrayList<>();
        for (Person p : persons) {
            list.add(p);
        }
        return list;
    }

    public Object[] addPersonLinkToList() {
        List<Person> persons = this.createListFromObject();

        Object[] streamPersons = StreamSupport.stream(persons.spliterator(),
                false).map(PersonLink::new)
                .toArray();
        return streamPersons;
    }

    public Person[] getRestPersons() {
        return restPersons;
    }

    public void setRestPersons(Person[] restPersons) {
        this.restPersons = restPersons;
    }

    public List<Person> getUtilPersons() {
        return utilPersons;
    }

    public void setUtilPersons(List<Person> utilPersons) {
        this.utilPersons = utilPersons;
    }

    public TestRestApi getTestRestApi() {
        return testRestApi;
    }

    public void setTestRestApi(TestRestApi testRestApi) {
        this.testRestApi = testRestApi;
    }
}

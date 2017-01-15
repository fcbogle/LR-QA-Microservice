package org.frank.bogle.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by frankbogle on 14/01/2017.
 */
@Document
public class Person {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String contactMobile;

    public Person(String email, String password){
        this.email = email;
    }

    public Person(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                '}';
    }
}

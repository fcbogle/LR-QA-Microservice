package org.frank.bogle.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Document
public class LRPerson {

    private static final Logger logger = LoggerFactory.getLogger(LRPerson.class);

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String lrProject;
    private String lrBusiness;

    public LRPerson(){}

    public LRPerson(String username) {
        this.userName = userName;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLrProject() {
        return lrProject;
    }

    public void setLrProject(String lrProject) {
        this.lrProject = lrProject;
    }

    public String getLrBusiness() {
        return lrBusiness;
    }

    public void setLrBusiness(String lrBusiness) {
        this.lrBusiness = lrBusiness;
    }

    @Override
    public String toString() {
        return "LRPerson{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", lrProject='" + lrProject + '\'' +
                ", lrBusiness='" + lrBusiness + '\'' +
                '}';
    }
}
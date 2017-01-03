package org.frank.bogle.repository;

import org.frank.bogle.model.LRPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Repository
public interface LRPersonRepository extends MongoRepository<LRPerson, String> {

    public List<LRPerson> findByFirstName(String firstName);

    public List<LRPerson> findByLastName(String lastName);

}
package org.frank.bogle.service;

import org.frank.bogle.model.LRPerson;
import org.frank.bogle.repository.LRPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by frankbogle on 20/12/2016.
 */
@Service
public class LRPersonService {

    private final LRPersonRepository lrPersonRepository;
    private static final Logger logger = LoggerFactory.getLogger(LRPersonService.class);

    @Autowired
    public LRPersonService(LRPersonRepository lrPersonRepository) {
        this.lrPersonRepository = lrPersonRepository;
    }

    public void saveLrPerson(LRPerson lrPerson) {
        logger.info("LRPersonService method saveLrPerson() invoked: " + LocalDateTime.now());
        this.lrPersonRepository.save(lrPerson);
    }

    public LRPerson findLrPersonById(String id) {
        logger.info("LRPersonService method findLrPersonById() invoked: " + LocalDateTime.now());
        LRPerson person = this.lrPersonRepository.findOne(id);
        return person;
    }

    public void deleteAllPersons() {
        logger.info("LRPersonService method deleteAllPersons() invoked: " + LocalDateTime.now());
        this.lrPersonRepository.deleteAll();
    }

    public List<LRPerson> getAllPersons() {
        logger.info("LRPersonService method getAllPersons() invoked: " + LocalDateTime.now());
        List<LRPerson> persons = this.lrPersonRepository.findAll();
        return persons;
    }
}

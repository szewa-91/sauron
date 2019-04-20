package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TestEntityPojo;
import com.sauron.model.entities.TestEntity;
import com.sauron.repo.TestEntityRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestEntityServiceImpl implements TestEntityService {

    private static final String INCORRECT_ID = "Incorrect ID";

    private TestEntityRepo testEntityRepo;

    public TestEntityServiceImpl(TestEntityRepo testEntityRepo) {
        this.testEntityRepo = testEntityRepo;
    }

    @Override
    public TestEntityPojo getTestEntity(long id) {
        Optional<TestEntity> testEntity = testEntityRepo.findTestEntityById(id);
        return testEntity
                .map(n -> new TestEntityPojo(n.getName()))
                .orElseThrow(() -> new EntityNotFoundException(INCORRECT_ID));
    }
}

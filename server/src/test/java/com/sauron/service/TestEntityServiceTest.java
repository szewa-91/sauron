package com.sauron.service;

import com.sauron.exception.EntityNotFoundException;
import com.sauron.model.TestEntityPojo;
import com.sauron.model.entities.TestEntity;
import com.sauron.repo.TestEntityRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TestEntityServiceTest {

    private TestEntityService testEntityService;
    private TestEntityRepo testEntityRepo;

    @Before
    public void setOff() {
        testEntityRepo = mock(TestEntityRepo.class);
        testEntityService = new TestEntityServiceImpl(testEntityRepo);
    }

    @Test
    public void validIdShouldReturnPojo() {
        //given
        long existingId = 1L;

        Optional<TestEntity> entity = Optional.ofNullable(new TestEntity(1L,"First"));
        Mockito.when(testEntityRepo.findTestEntityById(1L))
                .thenReturn(entity);
        TestEntityPojo expeted = new TestEntityPojo("First");

        //when
        TestEntityPojo actual = testEntityService.getTestEntity(existingId);

        //then
        assertEquals(expeted, actual);
    }

    @Test
    public void invalidIdShouldThrowException() {
        //given
        long missingId = 999L;

        //when
        Mockito.when(testEntityRepo.findTestEntityById(999L))
                .thenReturn(Optional.empty());
        Executable action = () -> testEntityService.getTestEntity(missingId);

        //then
        Assertions.assertThrows(EntityNotFoundException.class, action);
    }
}
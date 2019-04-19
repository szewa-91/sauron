package com.sauron.repo;

import com.sauron.model.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestEntityRepo extends JpaRepository<TestEntity, Long> {

    Optional<TestEntity> findTestEntityById(Long id);
}

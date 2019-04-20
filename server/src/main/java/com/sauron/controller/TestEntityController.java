package com.sauron.controller;

import com.sauron.model.TestEntityPojo;
import com.sauron.service.TestEntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-entities")
public class TestEntityController {

    private TestEntityService testEntityService;

    public TestEntityController(TestEntityService testEntityService) {
        this.testEntityService = testEntityService;
    }

    @GetMapping("/{id}")
    public TestEntityPojo getTestEntity(@PathVariable(name = "id") long id) {
        return testEntityService.getTestEntity(id);
    }
}

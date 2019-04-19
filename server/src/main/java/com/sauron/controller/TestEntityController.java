package com.sauron.controller;

import com.sauron.model.TestEntityPojo;
import com.sauron.service.TestEntitySerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-entities")
public class TestEntityController {

    private TestEntitySerivce testEntitySerivce;

    public TestEntityController(TestEntitySerivce testEntitySerivce) {
        this.testEntitySerivce = testEntitySerivce;
    }

    @GetMapping("/{id}")
    public TestEntityPojo getTestEntity(@PathVariable(name = "id") long id) {
        return testEntitySerivce.getTestEntity(id);
    }
}

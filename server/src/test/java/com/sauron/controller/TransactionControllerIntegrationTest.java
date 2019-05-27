package com.sauron.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
@Sql(value = "classpath:sql/accounts.sql") //TODO mock TransactionService
public class TransactionControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnAllTransactions() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
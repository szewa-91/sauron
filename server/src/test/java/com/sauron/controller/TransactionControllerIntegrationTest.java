package com.sauron.controller;

import com.sauron.service.BankServiceExecutor;
import com.sauron.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
@Sql(value = "classpath:sql/accounts.sql") //TODO mock TransactionService
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private TransactionService transactionService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new TransactionController(transactionService)).build();
    }

    @Test
    public void shouldReturnAllTransactions() throws Exception {
        given(transactionService.getAllTransactions()).willReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
package com.sauron.controller;

import com.sauron.service.CurrentBalanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/current-balance")
@CrossOrigin(origins = "http://localhost:4200") // TODO add HTTP proxy to avoid such hacks
public class CurrentBalanceController {

    private CurrentBalanceService currentBalanceService;

    public CurrentBalanceController(CurrentBalanceService currentBalanceService) {
        this.currentBalanceService = currentBalanceService;
    }

    @GetMapping
    public BigDecimal getAllTransactions() {
        return currentBalanceService.getCurrentBalance();
    }
}


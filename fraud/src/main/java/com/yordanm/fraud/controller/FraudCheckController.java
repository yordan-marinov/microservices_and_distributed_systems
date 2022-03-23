package com.yordanm.fraud.controller;

import com.yordanm.fraud.model.FraudCheckResponse;
import com.yordanm.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        final boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Customer with id: {} is been checked.", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}

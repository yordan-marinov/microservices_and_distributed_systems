package com.yordanm.fraud.controller;

import com.yordanm.fraud.model.FraudResponse;
import com.yordanm.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);

        return new FraudResponse(isFraudulentCustomer);
    }
}

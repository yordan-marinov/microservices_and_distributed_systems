package com.yordanm.fraud.controller;

import com.yordanm.fraud.model.FraudCheckResponse;
import com.yordanm.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @PostMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        final boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }
}

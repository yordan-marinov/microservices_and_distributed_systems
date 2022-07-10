package com.yordanm.apiclientssvc.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud-svc")
public interface FraudClient {

    @GetMapping(path = "/api/v1/fraud-check/{customerId}")
    FraudResponse isFraudster(@PathVariable("customerId") Integer customerId);
}

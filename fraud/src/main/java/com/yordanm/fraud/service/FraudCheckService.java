package com.yordanm.fraud.service;

import com.yordanm.fraud.model.FraudCheckHistory;
import com.yordanm.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDate.now())
                        .build()
        );
        return false;
    }
}


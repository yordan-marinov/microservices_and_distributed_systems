package com.yordanm.customer.service;

import com.yordanm.customer.models.Customer;
import com.yordanm.customer.models.CustomerRegistrationRequest;
import com.yordanm.customer.models.FraudCheckResponse;
import com.yordanm.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        final FraudCheckResponse response = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        assert response != null;
        if (response.isFraudster()){
            throw new RuntimeException("The customer is fraudster.");
        }


    }
}

package com.yordanm.customer.service;

import com.yordanm.apiclientssvc.fraud.FraudClient;
import com.yordanm.apiclientssvc.fraud.FraudResponse;
import com.yordanm.customer.model.Customer;
import com.yordanm.customer.model.CustomerRegistrationRequest;
import com.yordanm.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public Customer register(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        // THIS IS WHEN IS WITHOUT USING OF OPENFEIGN
//        FraudResponse response = restTemplate.getForObject(
////                "http://localhost:8990/api/v1/fraud-check/{customerId}", This way is when is done without the eureka client
//                "http://FRAUD-SVC/api/v1/fraud-check/{customerId}", // Done with eureka client name instead of localhost:port
//                FraudResponse.class,
//                customer.getId()
//        );

        FraudResponse fraudResponse = fraudClient.isFraudster(customer.getId());

        assert fraudResponse != null;
        if (fraudResponse.isFraudster()) {
            throw new IllegalStateException(
                    String.format("%s %s is fraudster.", customer.getFirstName(), customer.getLastName()));
        }
        return customer;
    }
}

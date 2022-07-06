package com.yordanm.customer.service;

import com.yordanm.customer.model.Customer;
import com.yordanm.customer.model.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer register(CustomerRegistrationRequest customerRegistrationRequest) {

        return Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
    }
}

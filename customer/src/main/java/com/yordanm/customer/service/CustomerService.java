package com.yordanm.customer.service;

import com.yordanm.customer.models.Customer;
import com.yordanm.customer.models.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
    }
}

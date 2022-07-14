package com.yordanm.customer.service;

import com.yordanm.amqp.RabbitMQMessageProducer;
import com.yordanm.apiclientssvc.fraud.FraudClient;
import com.yordanm.apiclientssvc.fraud.FraudResponse;
import com.yordanm.customer.model.Customer;
import com.yordanm.customer.model.CustomerRegistrationRequest;
import com.yordanm.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import com.yordanm.apiclientssvc.notification.NotificationClient;
import com.yordanm.apiclientssvc.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    // These are left as examples of different methods communications between the services
    private final RestTemplate restTemplate;  // Used for the restTemplate on localhost
    private final NotificationClient notificationClient;  // Used for openFeign interface client method


    public Customer register(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        // This is as example of the different usage of the method of service communication
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
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to our services ...", customer.getFirstName())
        );

        // Using OpenFeign with api-client-svc notification client interface
//        notificationClient.sendNotification(notificationRequest);

        // Using RabbitMQ - example
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

        return customer;
    }
}

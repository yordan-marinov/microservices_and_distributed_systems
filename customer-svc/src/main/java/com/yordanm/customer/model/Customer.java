package com.yordanm.customer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
}

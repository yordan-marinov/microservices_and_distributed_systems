package com.yordanm.customer.models;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email) {
}

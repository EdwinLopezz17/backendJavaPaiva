package com.learningcenter.profiles.interfaces.rest.resource;

public record CreateProfileResource(String firstName, String lastName, String email, String street,
                                    String number, String city, String state, String zipCode, String country) {



}

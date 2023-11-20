package com.learningcenter.learning.interfaces.net.resources;

public record CreateStudentResource(String name, String lastName, String email, String street,
                                    String number,String city, String state, String zipcode, String country) {
}

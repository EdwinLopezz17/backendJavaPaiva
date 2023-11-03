package com.learningcenter.learning.domain.model.commands;

public record CreateStudentCommand(String firstName, String LastName, String email, String streetAddress,
                                   String city, String state,String zipcode) {

}

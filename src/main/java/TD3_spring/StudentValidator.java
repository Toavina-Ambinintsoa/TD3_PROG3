package com.example.TD3_spring;

public class StudentValidator {

    public void validate(Student student) {

        if (student.getReference() == null || student.getReference().isBlank()) {
            throw new BadRequestException("Reference obligatoire");
        }

        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            throw new BadRequestException("First name obligatoire");
        }

        if (student.getLastName() == null || student.getLastName().isBlank()) {
            throw new BadRequestException("Last name obligatoire");
        }
    }
}
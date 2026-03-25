package com.example.TD3_spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService = new StudentService();
    private final StudentValidator studentValidator = new StudentValidator();

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {

        try {

            for (Student student : newStudents) {
                studentValidator.validate(student);
            }

            studentService.addStudents(newStudents);

            return ResponseEntity
                    .status(201)
                    .body(studentService.getStudents());

        } catch (BadRequestException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity
                    .status(500)
                    .body("Erreur serveur");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }
}
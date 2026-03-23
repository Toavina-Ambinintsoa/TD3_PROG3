package com.example.TD3_spring;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Name is required");
        }

        return ResponseEntity
                .ok("Welcome " + name);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {

        try {
            students.addAll(newStudents);

            return ResponseEntity
                    .status(201)
                    .body(students);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erreur serveur");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(
            @RequestHeader(value = "Accept", required = false) String accept) {

        try {
            if (accept == null) {
                return ResponseEntity
                        .badRequest()
                        .body("Header Accept requis");
            }

            if (accept.equals("text/plain")) {
                List<String> names = students.stream()
                        .map(s -> s.getFirstName() + " " + s.getLastName())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(names);
            }

            if (accept.equals("application/json")) {
                return ResponseEntity.ok(students);
            }

            return ResponseEntity
                    .status(501)
                    .body("Format non supporté");

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erreur serveur");
        }
    }
}
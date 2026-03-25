package com.example.TD3_spring;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
    }

    public List<Student> getStudents() {
        return students;
    }
}
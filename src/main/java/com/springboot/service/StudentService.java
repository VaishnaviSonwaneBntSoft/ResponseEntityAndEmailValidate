package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.entity.Student;

public interface StudentService {
    public void insertStudent(Student s);
    public List<Student> getAllStudents();
    public Optional<Student> getStudentsById(int id);
    public Student updateStudent(Student s , int id);
    public void deleteStudent(int id);
}

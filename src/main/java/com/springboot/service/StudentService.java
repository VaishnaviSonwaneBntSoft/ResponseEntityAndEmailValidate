package com.springboot.service;

import java.util.List;
import com.springboot.entity.Student;

public interface StudentService {
    public void insertStudent(Student s);
    public boolean existsByEmail(String email);
    public List<Student> getAllStudents();
    public Student updateStudent(Student s , int id);
    public void deleteStudent(int id);
}

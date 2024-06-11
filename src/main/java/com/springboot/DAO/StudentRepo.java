package com.springboot.DAO;

import java.util.List;

import com.springboot.entity.Student;

public interface StudentRepo {
    public void insertStudent(Student s);
    public List<Student> getAllStudents();
    public Student updateStudent(Student s , int id);
    public void deleteStudent(int id);
}

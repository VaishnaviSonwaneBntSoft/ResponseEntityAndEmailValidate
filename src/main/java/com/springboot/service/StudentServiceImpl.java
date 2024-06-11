package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.DAO.StudentRepo;
import com.springboot.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void insertStudent(Student s) {
        System.out.println("Hey i am in serviceImpl");
        studentRepo.insertStudent(s);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    @Override
    public Student updateStudent(Student s, int id) {
       return studentRepo.updateStudent(s, id);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepo.deleteStudent(id);
    }

}

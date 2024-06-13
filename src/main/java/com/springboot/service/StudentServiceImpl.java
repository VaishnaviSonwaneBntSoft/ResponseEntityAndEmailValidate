package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.DAO.StudentJpaRepo;
import com.springboot.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
   
    @Autowired
    private StudentJpaRepo studentJpaRepo;

    @Override
    public void insertStudent(Student s) {
        System.out.println("Hey i am in serviceImpl");
        studentJpaRepo.save(s);
    }

    @Override
    public List<Student> getAllStudents() {
        
        List<Student> studeList = new ArrayList<Student>();
        studeList = studentJpaRepo.findAll();
        return studeList;
    }

    @Override
    public Student updateStudent(Student s, int id) {
        return studentJpaRepo.save(s);
    }

    @Override
    public void deleteStudent(int id) {
        System.out.println("Hey i am in delete student");
        studentJpaRepo.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email){
        return studentJpaRepo.existsByEmail(email);
    }
}

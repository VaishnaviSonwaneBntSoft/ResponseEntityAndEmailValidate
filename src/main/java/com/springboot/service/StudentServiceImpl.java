
package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.DAO.StudentJpaRepo;
import com.springboot.Exception.DuplicateEmailEntryException;
import com.springboot.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
   
    @Autowired
    private StudentJpaRepo studentJpaRepo;

    @Override
    public void insertStudent(Student s) {
        boolean result = studentJpaRepo.existsByEmail(s.getEmail());
        if(result==true)
        {
            DuplicateEmailEntryException exception = new DuplicateEmailEntryException();
            throw exception;
        }else{
            studentJpaRepo.save(s);
        }
        System.out.println("Hey i am in serviceImpl");
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

}

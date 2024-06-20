
package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.DAO.StudentJpaRepo;
import com.springboot.Exception.DataNotFoundException;
import com.springboot.Exception.DuplicateEmailEntryException;
import com.springboot.Exception.StudentNotExitsOfProvidedId;
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
        boolean result = studentJpaRepo.existsById(id);
        if(result)
        {   return studentJpaRepo.save(s);
        }else{
            StudentNotExitsOfProvidedId exceptinNotExits = new StudentNotExitsOfProvidedId();
             throw exceptinNotExits;
        }
    }

    @Override
    public void deleteStudent(int id) {
        boolean result = studentJpaRepo.existsById(id);
        System.out.println("Hey i am in delete student" + result);
        if(result)
        {
            studentJpaRepo.deleteById(id);
        }else
        {
             StudentNotExitsOfProvidedId exceptinNotExits = new StudentNotExitsOfProvidedId();
             throw exceptinNotExits;
        }
    }

    @Override
    public Optional<Student> getStudentsById(int id) {

        Optional<Student> sOptional = studentJpaRepo.findById(id);

        if(sOptional.isEmpty())
        {
           throw new DataNotFoundException();
        }  
            return sOptional;

    }

}

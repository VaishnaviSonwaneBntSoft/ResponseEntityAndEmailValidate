package com.springBoot.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.DAO.StudentJpaRepo;
import com.springboot.entity.Student;

@SpringBootTest
public class StudentRepositoryUnitTasteCases {

    @Autowired
    private StudentJpaRepo studentJpaRepo;

    @Test
    public void saveEmployeeTestCase()
    {
        Student student = Student.builder()
                            .id(133)
                            .name("Sudha")
                            .rollno(55)
                            .email("sudha@gmail.com")
                            .build();
        Student savedstudnet = studentJpaRepo.save(student);
      
        assertNotNull(savedstudnet , "Object should not be null");
       
    }

    @Test
    public void getAllStudentsTestCase()
    {
        List<Student> list = studentJpaRepo.findAll();
        assertNotNull(list , "Returned data should not be null");
        assert(list).size()>1;
    }

    @Test
    public void deleteStudentTestCase()
    {
        Student student = Student.builder()
        .id(143)
        .name("Rushi")
        .rollno(15)
        .email("rushi@gmail.com")
        .build();
        Student savedstudnet = studentJpaRepo.save(student);
        assertNotNull(savedstudnet , "Object should not be null");

        Optional<Student> deletestudent = studentJpaRepo.findById(savedstudnet.getId());
        studentJpaRepo.deleteById(savedstudnet.getId());
        assert(deletestudent).isPresent();
    }

    @Test
    public void updateStudentTestCase()
    {
        Student student = Student.builder()
        .id(113)
        .name("akansha")
        .rollno(25)
        .email("akansha@gmail.com")
        .build();
        Student savedstudnet = studentJpaRepo.save(student);
        assertNotNull(savedstudnet , "Object should not be null");

        Optional<Student> getStudent = studentJpaRepo.findById(savedstudnet.getId());

        Student stud = getStudent.get();

        stud.setEmail("updatedEmail@gmail.com");

        Student updatedStudent = studentJpaRepo.save(stud);


        assertNotNull(updatedStudent , "Object should not be null");
        assert(updatedStudent.getEmail()).contentEquals("updatedEmail@gmail.com");
    }
}

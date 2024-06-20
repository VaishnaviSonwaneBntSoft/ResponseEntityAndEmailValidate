package com.springBoot.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.Exception.DataNotFoundException;
import com.springboot.Exception.DataNotPresentException;
import com.springboot.Exception.DuplicateEmailEntryException;
import com.springboot.Exception.StudentNotExitsOfProvidedId;
import com.springboot.Response.SuccsesResponse;
import com.springboot.controller.StudentController;
import com.springboot.entity.Student;

import com.springboot.service.StudentServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerUnitTestCases {

    @Mock
    StudentServiceImpl serviceImpl;

    @InjectMocks
    StudentController sController;

    @Test
    public void testAddStudent()
    {
       Student student = new Student(156,"Ghansham",31,"g@gmail.com");
       ResponseEntity<SuccsesResponse> rEntity = sController.addStudent(student);
        assertEquals(HttpStatus.CREATED, rEntity.getStatusCode());
    }

    @Test
    public void testGetStudent()
    {
        Student student = new Student(156,"Ghansham",31,"g@gmail.com");
        Student student1 = new Student(166,"phansham",21,"p@gmail.com");
        List<Student> studentList = new ArrayList<Student>();
        
        studentList.add(student1);
        studentList.add(student);

        when(serviceImpl.getAllStudents()).thenReturn(studentList);

        ResponseEntity<SuccsesResponse> rEntity = sController.getStudents();
        assertEquals(HttpStatus.OK, rEntity.getStatusCode());
    }
    
    @Test 
    public void testGetStudentById()
    {
        Optional<Student> student = Optional.of(new Student(156,"Ghansham",31,"g@gmail.com"));

        when(serviceImpl.getStudentsById(156)).thenReturn(student);

        ResponseEntity<SuccsesResponse> rEntity = sController.getStudentsById(156);

        assertEquals(HttpStatus.OK, rEntity.getStatusCode());
    }

    @Test
    public void testUpdateStudent()
    {
        Student oldstudent = new Student(156,"Ghansham",31,"g@gmail.com");
        Student newstudent = new Student(166,"phansham",21,"p@gmail.com");   

        when(serviceImpl.updateStudent(newstudent, oldstudent.getId())).thenReturn(newstudent);

        ResponseEntity<SuccsesResponse> rEntity = sController.updateStudent(newstudent, oldstudent.getId());

        assertEquals(HttpStatus.OK, rEntity.getStatusCode());
    }

    @Test
    public void testDeleteStudent()
    {
        Student student = new Student(156,"Ghansham",31,"g@gmail.com");
        
        ResponseEntity<SuccsesResponse> rEntity = sController.deleteStudent(student.getId());

        assertEquals(HttpStatus.OK, rEntity.getStatusCode());
    }

    //============================================== Negative Test cases ===================================================

    @Test
    public void testAddStudentFailed()
    {
        Student student = new Student(156, "Ghansham", 31, "g@gmail.com");

        doThrow(new DuplicateEmailEntryException()).when(serviceImpl).insertStudent(student);

        // Perform the test
        ResponseEntity<SuccsesResponse> rEntity = sController.addStudent(student);

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, rEntity.getStatusCode());

        assertNotNull(rEntity.getBody());

        assertEquals("This Email already present or taken , Please choose another email for entry", rEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, rEntity.getStatusCode());
    }

    @Test
    public void testGetStudentFailed()
    {
        List<Student> list =null;

        when(serviceImpl.getAllStudents()).thenReturn(list);

        when(sController.getStudents()==null).thenThrow(DataNotPresentException.class);

        assertThrows(DataNotPresentException.class, ()->{
            serviceImpl.getAllStudents();
            ResponseEntity<SuccsesResponse> entity = sController.getStudents();
            assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        });

    }

    @Test
    public void testGetStudentByIdFailed()
    {
        Optional<Student> sOptional=null;
        when(serviceImpl.getStudentsById(99)).thenReturn(sOptional);

        when(serviceImpl.getStudentsById(99)==null).thenThrow(DataNotFoundException.class);

        assertThrows(DataNotFoundException.class, ()->{
            serviceImpl.getStudentsById(99);
        });

        ResponseEntity<SuccsesResponse> sEntity = sController.getStudentsById(99);
        assertEquals(HttpStatus.BAD_REQUEST, sEntity.getStatusCode());
    }

    @Test
    public void testUpdateStudentFailed()
    {
        Student student = new Student(156,"Ghansham",31,"g@gmail.com");
        
        when(serviceImpl.updateStudent(student, 90)).thenThrow(StudentNotExitsOfProvidedId.class);
        
        assertThrows(StudentNotExitsOfProvidedId.class, ()-> {
            serviceImpl.updateStudent(student, 90);
        });

        ResponseEntity<SuccsesResponse> sEntity = sController.updateStudent(student, 90);
        assertEquals(HttpStatus.BAD_REQUEST, sEntity.getStatusCode());
    }

    @Test
    public void testDeleteStudentFailed()
    {
    doThrow(new StudentNotExitsOfProvidedId()).when(serviceImpl).deleteStudent(99);

    // Perform the test
    ResponseEntity<SuccsesResponse> rEntity = sController.deleteStudent(99);

         // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, rEntity.getStatusCode());
      assertNotNull(rEntity.getBody());
    }
}

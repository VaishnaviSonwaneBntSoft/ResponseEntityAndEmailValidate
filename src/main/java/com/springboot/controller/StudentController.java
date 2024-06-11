package com.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Student;
import com.springboot.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    public StudentController() {
        System.out.println("Hey i am in controller");
    }

    @Autowired
    private StudentService service;

    @PostMapping("/addstudent")
    public void addStudent(@RequestBody Student s) {
        service.insertStudent(s);
    }

    @GetMapping("/getstudent")
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudent(@PathVariable("id") int id)
    {   service.deleteStudent(id);
    }

    @PutMapping("/updatestudent/{id}")
    public void updateStudent(@RequestBody Student s , @PathVariable("id") int id)
    {   if(service.updateStudent(s, id)!=null)
            System.out.println("Updated succesfully");
        else
            System.out.println("Not Updated succesfully");
    }

}

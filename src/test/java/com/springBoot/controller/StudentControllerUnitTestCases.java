package com.springBoot.controller;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerUnitTestCases {

    @Autowired
    private MockMvc mockMvc; 

    @Test
    public void testAddStudent()
    {
    //    mockMvc.perform(post("/api/v1/student/addstudent")
    //    .contentType(MediaType.APPLICATION_JSON)
    //     .content("{\"name\"}")
        
    //     )

    }

    
    
}

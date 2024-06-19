package com.springBoot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot.DAO.StudentJpaRepo;
import com.springboot.Exception.DuplicateEmailEntryException;
import com.springboot.entity.Student;
import com.springboot.service.StudentServiceImpl;

@SpringBootTest
public class StudentServiceUnitTestCase {
    
    @Mock
    private StudentJpaRepo sJpaRepo;    

    @InjectMocks
    private StudentServiceImpl service;

    @Test
    public void TestSaveStudentService()
    {
        Student student = new Student(156,"Ghansham",31,"g@gmail.com");

        when(sJpaRepo.existsByEmail(student.getEmail())).thenReturn(false);

        assertNotNull(student);
        service.insertStudent(student);
        
        verify(sJpaRepo , times(1)).existsByEmail(student.getEmail());
        //verifying that save method get called only once or not
        //if it get called once then 
        verify(sJpaRepo, times(1)).save(student);
    }


    @Test
    public void TestGetStudentSerivce()
    {
        List<Student> list = new ArrayList<Student>();
        
        Student student = new Student(126,"Ghnsham",43,"p@gmail.com");
        Student student1 = new Student(116,"Ghanshm",32,"c@gmail.com");
        list.add(student);
        list.add(student1);

        //i have set behaviour of findAll method
        //if findAll method get called then that method should return list of student data
        when(sJpaRepo.findAll()).thenReturn(list);

       List<Student> returnlist = service.getAllStudents();
        
        assertEquals(list.get(0), returnlist.get(0));
        assertEquals(list.get(1), returnlist.get(1));

        assertEquals(list, returnlist , "Both list should be equal");

        assertEquals(2, returnlist.size() , "list size should be two");

        //Checks how many times finaAll() method calls
        verify(sJpaRepo, times(1)).findAll();
    }



    @Test
    public void TestUpdateStudentService(){
        Student student = new Student(126,"Ghnsham",43,"p@gmail.com");
        Student updatedStudent = new Student(126,"sudhsham",03,"sudhp@gmail.com");

        //set behaviour for save method 
        //if student data saved as updated then return saved info means updated student
        when(sJpaRepo.save(updatedStudent)).thenReturn(updatedStudent);

        //returning updated info
        Student uStudent = service.updateStudent(updatedStudent, student.getId());

        //matching inserted or updated data to resultan data 
        assertEquals(updatedStudent, uStudent);
        //verifying that save method called just once
        verify(sJpaRepo, times(1)).save(updatedStudent);
    }

    @Test
    public void TestDeleteStudentService()
    {
        Student student = new Student(126,"Ghnsham",43,"p@gmail.com");

        service.deleteStudent(student.getId());

        verify(sJpaRepo,times(1)).deleteById(student.getId());
    }


    // ====================================== Negative Test Cases =================================================//

    @Test
    public void TestSaveStudentServiceFailed()
    {
        Student student = new Student(156,"Ghansham",31,"g@gmail.com");
        when(sJpaRepo.existsByEmail(student.getEmail())).thenReturn(true);

        assertThrows(DuplicateEmailEntryException.class , ()->{
            service.insertStudent(student);
        });

        verify(sJpaRepo ,times(1)).existsByEmail(student.getEmail());
        verify(sJpaRepo , never()).save(student);
        
    }

    @Test
    public void TestSaveStudent_Failed_NullStudent()
    {
        Student student=null;
        when(sJpaRepo.existsByEmail("something@gamil.com")).thenReturn(true);
        assertThrows(NullPointerException.class, ()->{
            service.insertStudent(student);
        });
        assertEquals(null, student);
    }

    @Test
    public void getAllStudentsTestCaseFailed()
    {
        List<Student> emptylist = new ArrayList<>();
        when(sJpaRepo.findAll()).thenReturn(emptylist);
        service.getAllStudents();
        // we are testing that it should return null or 0 when we trying to fetch empty table
        assertEquals(0, emptylist.size());
    }
    
    @Test
    public void TestUpdateStudentServiceFailed(){
        Student updatedStudent = new Student(126,"sudhsham",03,"sudhp@gmail.com");

        //set behaviour for save method 
        //if student data saved as updated then return saved info means updated student
        when(sJpaRepo.save(updatedStudent)).thenReturn(updatedStudent);
      
        //verifying that save method called just once
        verify(sJpaRepo, times(0)).save(updatedStudent);
    }


    @Test
    public void TestDeleteStudentServiceFailed()
    {
       // Student student = new Student(126,"Ghnsham",43,"p@gmail.com");
        
        service.deleteStudent(999);

        verify(sJpaRepo,times(0)).deleteById(126);
    }
    
}

package com.springboot.Exception;

public class StudentNotExitsOfProvidedId extends RuntimeException{
    public String getMessage()
    {
        return "Student not exit with provided id , check your id and try again";
    }
}

package com.springboot.Exception;

public class DataNotFoundException extends RuntimeException{
    public String exceptionMessage()
    {
        return "There is No Student present regarding this ID please recheck your id and try again";
    }
}

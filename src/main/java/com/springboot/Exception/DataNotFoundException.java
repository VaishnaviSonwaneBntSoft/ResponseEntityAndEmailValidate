package com.springboot.Exception;

public class DataNotFoundException extends RuntimeException{
    public String exceptionMessage()
    {
        return "There is No data present regarding this ID please recheck your id and try again";
    }
}

package com.springboot.Exception;

public class DataNotPresentException extends RuntimeException{

    public String getMessage()
    {
        return "No Data Present in Database";
    }
}

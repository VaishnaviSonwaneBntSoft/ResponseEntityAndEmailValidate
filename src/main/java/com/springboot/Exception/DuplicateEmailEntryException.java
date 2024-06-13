package com.springboot.Exception;

public class DuplicateEmailEntryException extends RuntimeException{
    public String reportDuplicateEmailError()
    {
        return "This Email already present or taken , Please choose another email for entry";
    }
}

package com.springboot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.Response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionStudent extends Exception{

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(DataNotFoundException dNotFoundException)
    {
        ErrorResponse eResponse = new ErrorResponse(dNotFoundException.exceptionMessage() , HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorResponse>(eResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotPresentException.class)
    public ResponseEntity<ErrorResponse> dataNotPresentExceptionHandler(DataNotPresentException dNotPresentException)
    {
        ErrorResponse errorResponse = new ErrorResponse(dNotPresentException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorResponse>(errorResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailEntryException.class)
    public ResponseEntity<ErrorResponse> duplicateEmailEntryExceptionHandler(DuplicateEmailEntryException duplicateEmailEntryException)
    {
        ErrorResponse errorResponse = new ErrorResponse(duplicateEmailEntryException.reportDuplicateEmailError() ,  HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<ErrorResponse>(errorResponse , HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(StudentNotExitsOfProvidedId.class)
    public ResponseEntity<ErrorResponse> studentNotExitsOfProvidedId(StudentNotExitsOfProvidedId sNotExitsOfProvidedId)
    {
        ErrorResponse errorResponse = new ErrorResponse(sNotExitsOfProvidedId.getMessage() , HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

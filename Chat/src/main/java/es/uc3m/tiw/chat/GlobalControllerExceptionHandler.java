package es.uc3m.tiw.chat;

import java.io.IOException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    
	@ResponseStatus(HttpStatus.CONFLICT)  // Error HTTP 409
    @ExceptionHandler(IOException.class)
    public void handleConflictConflict() {
//        System.out.println("Se ha producido una excepción de tipo IOException - Error HTTP 409");
    }
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Error HTTP 404
    @ExceptionHandler(NotFoundException.class)
    public void handleConflictNotFound() {
//        System.out.println("Se ha producido una excepción de tipo NotFoundException - Error HTTP 404");
    }
}
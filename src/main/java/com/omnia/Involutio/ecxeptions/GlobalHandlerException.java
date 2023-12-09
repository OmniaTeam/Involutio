package com.omnia.Involutio.ecxeptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler
    public ResponseEntity<?> userNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage() + " not found");
    }
    @ExceptionHandler
    public ResponseEntity<?> workerNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage() + " not found");
    }
}

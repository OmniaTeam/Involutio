package com.omnia.Involutio.ecxeptions;

public class WorkerNotFoundException extends RuntimeException{
    WorkerNotFoundException(String message) {
        super(message);
    }
}

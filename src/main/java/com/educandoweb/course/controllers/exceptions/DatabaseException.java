package com.educandoweb.course.controllers.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException (String msg) {
        super(msg);
    }
}

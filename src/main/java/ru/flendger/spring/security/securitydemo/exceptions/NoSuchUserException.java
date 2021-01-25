package ru.flendger.spring.security.securitydemo.exceptions;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String msg) {
        super(msg);
    }
}

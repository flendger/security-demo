package ru.flendger.spring.security.securitydemo.exceptions;

public class NeedAuthorisationException extends RuntimeException{
    public NeedAuthorisationException(String message) {
        super(message);
    }
}

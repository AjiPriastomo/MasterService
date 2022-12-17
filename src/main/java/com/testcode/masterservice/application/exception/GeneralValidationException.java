package com.testcode.masterservice.application.exception;

public class GeneralValidationException extends RuntimeException{

    private static final long serialVersionUID = 4112756003681927593L;

    public GeneralValidationException(String message) {
        super(message);
    }

}

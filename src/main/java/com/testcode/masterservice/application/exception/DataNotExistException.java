package com.testcode.masterservice.application.exception;

public class DataNotExistException extends RuntimeException{
    private static final long serialVersionUID = 4112756003681927593L;

    public DataNotExistException() {
        super("Data does not exist");
    }

}

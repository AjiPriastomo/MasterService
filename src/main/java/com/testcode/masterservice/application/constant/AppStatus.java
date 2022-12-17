package com.testcode.masterservice.application.constant;

public enum AppStatus {
    SUCCESS(200, "Success"),
    ERROR_VALIDATION(400, "Error validation"),
    ERROR_UNAUTHORIZE(401, "Error unauthorized"),
    ERROR_FORBIDDEN(403, "Error forbidden"),
    ERROR_NOT_FOUND(404, "Resource not found"),
    ERROR_INTERNAL(500, "Internal server error");

    private final Integer status;

    private final String message;

    AppStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

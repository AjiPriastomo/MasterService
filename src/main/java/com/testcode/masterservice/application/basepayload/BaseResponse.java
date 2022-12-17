package com.testcode.masterservice.application.basepayload;

import com.testcode.masterservice.application.constant.AppStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseResponse<DATA> {

    private Long timestamp;
    protected Integer status;
    protected String message;
    private DATA results;

    public BaseResponse(DATA results) {
        super();
        this.results = results;
        this.status = AppStatus.SUCCESS.getStatus();
        this.message = AppStatus.SUCCESS.getMessage();
        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000l;
    }

    public BaseResponse() {
        this.status = AppStatus.SUCCESS.getStatus();
        this.message = AppStatus.SUCCESS.getMessage();
    }

    public BaseResponse(String message, Integer status, DATA resutls) {
        super();
        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000l;
        this.message = message;
        this.status = status;
        this.results = resutls;
    }

    public BaseResponse(AppStatus status) {
        super();
        this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000l;
        this.message = status.getMessage();
        this.status = status.getStatus();
    }
}

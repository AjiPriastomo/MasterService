package com.testcode.masterservice.module.officer.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficerRequest {

    private String name;

    private String department;

}

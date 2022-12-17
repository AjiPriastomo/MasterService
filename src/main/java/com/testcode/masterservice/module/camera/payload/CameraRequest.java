package com.testcode.masterservice.module.camera.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraRequest {

    private String name;

    @JsonProperty("serial_number")
    private String serialNumber;

    private String type;

    private String model;

    private String brand;

    @JsonProperty("unit_id")
    private Long unitId;
}

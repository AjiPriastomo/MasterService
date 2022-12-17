package com.testcode.masterservice.module.camera.payload;

import com.testcode.masterservice.module.unit.payload.UnitResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CameraResponse {


    private Integer id;

    private String name;

    @JsonProperty("serial_number")
    @JsonAlias("serialNumber")
    private String serialNumber;

    private String type;

    private String model;

    private String brand;

    @JsonProperty("add_date")
    @JsonAlias("addDate")
    private LocalDateTime addDate;

    private UnitResponse unit;

}

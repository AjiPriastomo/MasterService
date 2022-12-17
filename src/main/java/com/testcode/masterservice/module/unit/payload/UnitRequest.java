package com.testcode.masterservice.module.unit.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitRequest {

    private String name;

    private String description;

    @JsonProperty("unit_type")
    private String unitType;
}

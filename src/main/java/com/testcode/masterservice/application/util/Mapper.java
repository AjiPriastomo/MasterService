package com.testcode.masterservice.application.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class Mapper {

    private ObjectMapper objectMapper;

    public Mapper() {
        super();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T parse(Class<T> clazz, String jsonString) {
        return Optional.ofNullable(jsonString).map(json -> {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);
    }

    public <T> T parseTypeRef(TypeReference typeRef, String jsonString) {
        try {
            return (T) objectMapper.readValue(jsonString, typeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T parseTypeRef(TypeReference<T> typeRef, Object object) {
        return objectMapper.convertValue(object, typeRef);
    }

    public String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T toObject(Class<T> clazz, Object object) {
        return Optional.ofNullable(object).map(obj -> {
            return objectMapper.convertValue(obj, clazz);
        }).orElse(null);
    }

    public <T> T mapObject(Object object, Class<T> clazz) {
        return Optional.ofNullable(object).map(obj -> {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            return modelMapper.map(obj, clazz);
        }).orElse(null);
    }

    public JsonNode toNode(String object) {
        try {
            return objectMapper.readTree(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

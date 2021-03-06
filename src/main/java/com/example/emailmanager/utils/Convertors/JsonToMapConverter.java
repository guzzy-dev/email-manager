package com.example.emailmanager.utils.Convertors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JsonToMapConverter implements AttributeConverter<String, Map<String, String>> {


    @Override
    public Map<String, String> convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return new HashMap<>();
        }
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(attribute, HashMap.class);
        }
        catch (IOException e) {
            System.out.println("Convert error while trying to convert string(JSON) to map data structure.");
        }
        return new HashMap<>();
    }

    @Override
    public String convertToEntityAttribute(Map<String, String> stringStringMap) {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(stringStringMap);
        }
        catch (JsonProcessingException e)
        {
            System.out.println("Could not convert map to json string.");
            return "";
        }
    }
}

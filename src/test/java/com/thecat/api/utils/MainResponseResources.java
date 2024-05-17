package com.thecat.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecat.api.dtos.MainResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainResponseResources {
    private MainResponseResources(){}
    public static MainResponseDto convertToMainResponseFromJSON(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(json, MainResponseDto.class);
        } catch(JsonProcessingException jsonProcessingException) {
            log.error("Could not decode the Json");
        }
        return null;
    }
}

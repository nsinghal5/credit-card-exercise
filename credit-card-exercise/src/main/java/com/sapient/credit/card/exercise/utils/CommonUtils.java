package com.sapient.credit.card.exercise.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String asJsonString(Object object) throws JsonProcessingException {
        if(object == null) return null;

        return mapper.writeValueAsString(object);
    }
}

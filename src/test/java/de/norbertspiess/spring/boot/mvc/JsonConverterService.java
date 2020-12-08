package de.norbertspiess.spring.boot.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonConverterService {
    private final ObjectMapper mapper;

    public JsonConverterService() {
        this.mapper = new ObjectMapper();
    }

    public JsonConverterService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String toJson(Object source) throws JsonProcessingException {
        return mapper.writeValueAsString(source);
    }

    public <T> T toObject(String json, Class<T> target) throws JsonProcessingException {
        return mapper.readValue(json, target);
    }

    public <T> List<T> toObjectList(String json, Class<T> target) throws JsonProcessingException {
        return mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionLikeType(List.class, target));
    }
}

package ru.otus.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;

@SuppressWarnings("unused")
public class JsonUtils {

    private final ObjectMapper mapper;

    public JsonUtils() {
        this.mapper = new ObjectMapper();
    }

    @SneakyThrows
    public String encode(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public <T> T decode(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @SneakyThrows
    public <T> List<T> decodeList(String json, Class<T> clazz) {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

}

package com.anastasiiat.sweetshop.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

public class JSONUtils {

    public static Map<String, Object> parseJSON(String json) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        return parser.parseMap(json);
    }

    public static <T> T jsonToObject(String json, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(parseJSON(json), valueType);
    }
}

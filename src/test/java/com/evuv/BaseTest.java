package com.evuv;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public final static ObjectMapper mapper = new ObjectMapper();

	public String convertObjectToString(Object object) throws JsonProcessingException {
		String json = mapper.writeValueAsString(object);
		return json;
	}

	public Map<String, Object> convertObjectToMap(Object object) throws IOException {
		String json = mapper.writeValueAsString(object);
		return convertJsonToMap(json);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> convertJsonToMap(String json)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> map = mapper.readValue(json, HashMap.class);
		return map;
	}

	public <T> T convertJsonToObject(String json, Class<T> objectClass)
			throws JsonParseException, JsonMappingException, IOException {
		T t = mapper.readValue(json, objectClass);
		return t;
	}

	public <T> T convertMapToObject(Map<String, Object> values, Class<T> objectClass) throws IOException {
		String jsonString = convertObjectToString(values);

		return convertJsonToObject(jsonString, objectClass);

	}
}

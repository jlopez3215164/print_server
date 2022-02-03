package com.pos.util.jersey.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class XJsonUtil {

	public XJsonUtil() {
		// TODO Auto-generated constructor stub
	}

	public <T> T loadFromFile(String url, Class<T> objectClass) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(url), objectClass);
		} finally{
			// TODO: handle exception
			mapper=null;
		}
		
	}
	
	public <T> T jsonDataToObject(Class<T> objectClass, String value) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {	
			return new ObjectMapper().readValue(value, objectClass);
		} finally{
			// TODO: handle exception
			mapper=null;
		}
	}
	
	public void saveToFile(String url, Object data) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(url), data);
		} finally{
			// TODO: handle exception
			objectMapper=null;
		}
	}
	
	public void saveToFileUnix(String url, Object data) throws JsonProcessingException{
		ObjectWriter ow;
		String jsonFinal=null;
		PrintWriter writer = null;
		try {
			ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			jsonFinal = ow.writeValueAsString(data);
			writer = new PrintWriter(url,"UTF-8");
			writer.println(jsonFinal.replaceAll("\\r\\n", "\n"));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle exception
			ow=null;
			writer = null;
		}
	}
	
	public String generateString(Object ob) throws JsonProcessingException{
		ObjectWriter ow;
		String resul=null;
		try {
			ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			resul = ow.writeValueAsString(ob);
		} finally {
			// TODO: handle exception
			ow=null;
		}
		return resul;
	}
}

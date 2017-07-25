package com.Shell.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;



public class CustomDateSerializer extends JsonSerializer<Long> {  
	  
    @Override  
    public void serialize(Long value,   
            JsonGenerator jsonGenerator,   
            SerializerProvider provider)  
            throws IOException, JsonProcessingException {  
    	 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	    String d = format.format(value);  
    	    Date date = null;
			try {
				date = format.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
        jsonGenerator.writeString(date.toString());  
    }
}
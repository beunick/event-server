package com.event.info;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


import com.event.info.entity.Event;
import com.event.info.service.EventService;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
//@EnableCaching
public class EventsApplication {

	@Autowired
	EventService eventService;
	
	@Value("${path.of.json.file.to.load}")
	String pathOfJsonFileToLoad;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner runner() { 
		return args -> {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			TypeReference<List<Event>> typeRef = new TypeReference<List<Event>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream(pathOfJsonFileToLoad);
			try{
				List<Event> events = mapper.readValue(inputStream, typeRef);
				eventService.saveAllEvent(events);
				
			}catch(IOException ioException) {
				LOGGER.info("*** Unable to load the JSON event Data {}", ioException.getMessage());
			}
		};
	}


}


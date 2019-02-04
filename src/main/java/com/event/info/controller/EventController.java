package com.event.info.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.info.dto.EventDetailDto;
import com.event.info.dto.EventDisplayDto;
import com.event.info.dto.EventRequestDto;
import com.event.info.dto.PageEventDisplayDto;
import com.event.info.entity.Event;
import com.event.info.service.EventService;

@RestController 
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class EventController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	public ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/events")
	@ResponseBody
	public PageEventDisplayDto getEventsDisplayAll( Pageable pageable){	
		Page<Event> eventsDisplayInfo = eventService.getAllEvent(pageable);
		List<EventDisplayDto> eventDisplayDtoList = eventsDisplayInfo.getContent().stream().map(event -> this.convertToEventDisplayDto(event)).collect(Collectors.toList());
		
		
		return new PageEventDisplayDto(eventDisplayDtoList,eventsDisplayInfo.getNumber(), eventsDisplayInfo.getNumberOfElements(),
				eventsDisplayInfo.getSize(),eventsDisplayInfo.getSort(), eventsDisplayInfo.getTotalElements(),eventsDisplayInfo.getTotalPages()  );
	}
	
	@GetMapping("/events/period")
	@ResponseBody
	public PageEventDisplayDto getEventsByPeriod(EventRequestDto eventReqDto){
	
		Pageable pageable =  PageRequest.of(eventReqDto.getPage(), eventReqDto.getSize(), Sort.Direction.ASC, eventReqDto.getSort());
		
		Page<Event> eventsDisplayInfo = this.eventService.getEventByPeriod(eventReqDto.getEventDate(), eventReqDto.getEventPeriod(), pageable);
		List<EventDisplayDto> eventDisplayDtoList = eventsDisplayInfo.stream().map(event -> this.convertToEventDisplayDto(event)).collect(Collectors.toList());
		
		return new PageEventDisplayDto(eventDisplayDtoList,eventsDisplayInfo.getNumber(), eventsDisplayInfo.getNumberOfElements(),
				eventsDisplayInfo.getSize(),eventsDisplayInfo.getSort(), eventsDisplayInfo.getTotalElements(),eventsDisplayInfo.getTotalPages()  );
	}
	
	@GetMapping("/event")
	@ResponseBody
	public EventDetailDto getEventById(Long id){
		
		Event eventDetailInfo = eventService.getEventById(id);
		return this.convertToEventDetailDto(eventDetailInfo);
	}
	
	public EventDetailDto convertToEventDetailDto(Event event) {
		return modelMapper.map(event, EventDetailDto.class);
	}
	
	public EventDisplayDto convertToEventDisplayDto(Event event) {
		
		LOGGER.info("*** Event to Map {}, {}, {}", event.getEventDate(), event.getEventType(), event.getEventSize());
		return modelMapper.map(event, EventDisplayDto.class);	
	}
	
	
}

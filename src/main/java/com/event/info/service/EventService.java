package com.event.info.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;


import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.event.info.repository.EventRepository;
import com.event.info.entity.*;

@Service
public class EventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
	
	private final String WEEK="week";
	private final String MONTH="month";
	private final String QUARTER="quarter";
	

	
	@Autowired
	private EventRepository eventRepo;
	
	
	public void saveAllEvent(List<Event> events) {
		
		eventRepo.saveAll(events);
	}
	
	//@Cacheable("allEvent")
	public Page<Event> getAllEvent(Pageable pageable){
		
		return eventRepo.findAll(pageable);
	}
	
	//@Cacheable("eventById")
	public Event getEventById(Long id) {
		return this.eventRepo.findEventById(id);
	}
	
	//@Cacheable("eventsForPeriod")
	public Page<Event> getEventByPeriod(String startDate, String periodName, Pageable pageable){
				
		LocalDate initialDate = LocalDate.parse(startDate);
		Page<Event> listOfEventForPeriod = null;
		
		if(periodName.equalsIgnoreCase(WEEK)) {

			DayOfWeek dayOfWeek = initialDate.getDayOfWeek();
			LocalDate firstDayOfTheWeek;
			switch (dayOfWeek.getValue()) {
				case 1: // Monday
					firstDayOfTheWeek = initialDate;
					break;
				case 2: // Tuesday
					firstDayOfTheWeek = initialDate.minusDays(1);
					break;
				case 3: // Wednesday
					firstDayOfTheWeek = initialDate.minusDays(2);
					break;
				case 4: // Thursday
					firstDayOfTheWeek = initialDate.minusDays(3);
					break;
				case 5: // Friday
					firstDayOfTheWeek = initialDate.minusDays(4);
					break;
				case 6: // Saturday
					firstDayOfTheWeek = initialDate.minusDays(5);
					break;
				case 7: // Sunday
					firstDayOfTheWeek = initialDate.minusDays(6);
					break;
				default:
					firstDayOfTheWeek = initialDate;
					break;
			}
	
			LocalDate lastDayOfTheWeek  = firstDayOfTheWeek.plusWeeks(1).minusDays(1);
			
			LOGGER.info(" Load Data between {} and {} of the Week ", firstDayOfTheWeek, lastDayOfTheWeek);
			listOfEventForPeriod = this.eventRepo.findAllByEventDateBetween(java.sql.Date.valueOf(firstDayOfTheWeek) 
																,java.sql.Date.valueOf(lastDayOfTheWeek), pageable);
			
		
		}else if(periodName.equalsIgnoreCase(MONTH)) {
			
			LocalDate firstDayOfTheMonth = initialDate.with(firstDayOfMonth());
			LocalDate lastDayOfTheMonth  = initialDate.with(lastDayOfMonth());
			
			LOGGER.info(" Load data between {} and {} of the Month of {}", firstDayOfTheMonth, lastDayOfTheMonth, initialDate.getMonth() );
			listOfEventForPeriod = this.eventRepo.findAllByEventDateBetween(java.sql.Date.valueOf(firstDayOfTheMonth) 
														,java.sql.Date.valueOf(lastDayOfTheMonth), pageable);

			
		}else if(periodName.equalsIgnoreCase(QUARTER)) {
			
			int currentQuarter = initialDate.get(IsoFields.QUARTER_OF_YEAR);
			
			LocalDate firstDayOfQuarter;
			LocalDate lastDayOfQuarter;
			
			switch (currentQuarter){
				case 1: // first Quarter
					firstDayOfQuarter = initialDate.with(TemporalAdjusters.firstDayOfYear());
					break;
					
				case 2: // second Quarter April-Jun
					firstDayOfQuarter = initialDate.withMonth(Month.APRIL.getValue()).with(TemporalAdjusters.firstDayOfMonth());
					break;
					
				case 3: // third Quarter Jul-Sep
					firstDayOfQuarter = initialDate.withMonth(Month.JULY.getValue()).with(TemporalAdjusters.firstDayOfMonth());
					break;

				case 4: // fourth Quarter Oct-Dec
					firstDayOfQuarter = initialDate.withMonth(Month.OCTOBER.getValue()).with(TemporalAdjusters.firstDayOfMonth());
					break;
				
				default:
					firstDayOfQuarter = initialDate;				
					break;
			}
			
			lastDayOfQuarter 	= firstDayOfQuarter.plusMonths(2).with(lastDayOfMonth());
			
			LOGGER.info(" Load Data for the quarter # {}, between {} and {} ", currentQuarter, firstDayOfQuarter,  lastDayOfQuarter );	
			
			listOfEventForPeriod = this.eventRepo
											.findAllByEventDateBetween(java.sql.Date.valueOf(firstDayOfQuarter) 
																			, java.sql.Date.valueOf(lastDayOfQuarter), pageable);

		}
			
		return listOfEventForPeriod; 
	}
}

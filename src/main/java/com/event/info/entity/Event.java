package com.event.info.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonSerialize
	@JsonProperty("event_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date eventDate;
	
	@JsonSerialize
	@JsonProperty("event_type")
    private String eventType;
    
	@JsonSerialize
	@JsonProperty("event_summary")
    private String eventSummary;
    
	@JsonSerialize
	@JsonProperty("event_size")
    private int eventSize;
    

    @Lob
	@JsonSerialize
	@JsonProperty("event_details")
    private String eventDetails;
    
    
    public Event() {}

    

	public Event(Date eventDate, String eventType, String eventSummary, int eventSize, String eventDetails) {
		super();
		this.eventDate = eventDate;
		this.eventType = eventType;
		this.eventSummary = eventSummary;
		this.eventSize = eventSize;
		this.eventDetails = eventDetails;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public String getEventSummary() {
		return eventSummary;
	}


	public void setEventSummary(String eventSummary) {
		this.eventSummary = eventSummary;
	}


	public int getEventSize() {
		return eventSize;
	}


	public void setEventSize(int eventSize) {
		this.eventSize = eventSize;
	}


	public String getEventDetails() {
		return eventDetails;
	}


	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
    
  
	
	
}

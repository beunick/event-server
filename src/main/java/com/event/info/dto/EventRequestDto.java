package com.event.info.dto;

public class EventRequestDto {

	private String eventDate;
	private String eventPeriod;
	private String sort; //Field of Sorting
	private String sortType;
	private int size;
	private int page;
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventPeriod() {
		return eventPeriod;
	}
	public void setEventPeriod(String period) {
		this.eventPeriod = period;
	}
	

}

package com.event.info.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

public class PageEventDisplayDto {

	private List<EventDisplayDto> eventDisplayDto;
	private int number; 
	private int numberOfElement; 
	private int size; 
	private Sort sort; 
	private long totalElement; 
	private int totalPages;
	
	public PageEventDisplayDto(List<EventDisplayDto> eventDisplayDto, int number, int numberOfElement, int size,
			Sort sort, long totalElement, int totalPages) {
		super();
		this.eventDisplayDto = eventDisplayDto;
		this.number = number;
		this.numberOfElement = numberOfElement;
		this.size = size;
		this.sort = sort;
		this.totalElement = totalElement;
		this.totalPages = totalPages;
	}
	public List<EventDisplayDto> getEventDisplayDto() {
		return eventDisplayDto;
	}
	public void setEventDisplayDto(List<EventDisplayDto> eventDisplayDto) {
		this.eventDisplayDto = eventDisplayDto;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumberOfElement() {
		return numberOfElement;
	}
	public void setNumberOfElement(int numberOfElement) {
		this.numberOfElement = numberOfElement;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Sort isSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	} 
	
	
}

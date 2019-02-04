package com.event.info.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.event.info.entity.*;
public interface EventRepository extends CrudRepository<Event, Long> {

	//List<Event> findAllByEventDateBetween(Date startDate, Date endDate, Pageable pageable);
	Page<Event> findAllByEventDateBetween(Date startDate, Date endDate, Pageable pageable);
	
	
	Page<Event> findAll(Pageable pageable);
	Event findEventById(Long id);
	
	
}

package com.maurevair.booking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maurevair.booking.entity.AirportDetails;
import com.maurevair.booking.entity.FlightDetails;

@Repository
public interface FlightRepository extends CrudRepository<FlightDetails, String>{
	
	public List<FlightDetails> findByOriginAndDestination(String origin, String destination);

}

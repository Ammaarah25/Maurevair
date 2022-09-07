package com.maurevair.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maurevair.booking.entity.AirportDetails;

@Repository
public interface AirportRepository extends JpaRepository<AirportDetails, String>{
	
	public List<AirportDetails> findByairportCode(String airportCode);
	

}

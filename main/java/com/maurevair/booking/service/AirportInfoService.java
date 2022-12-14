package com.maurevair.booking.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.maurevair.booking.entity.AirportDetails;
import com.maurevair.booking.helper.AirportInfoHelper;
import com.maurevair.booking.repository.AirportRepository;

@Service
public class AirportInfoService {
	
	  @Autowired
	  AirportRepository airportRepository;
	  
	  public void save(MultipartFile file) {
	    try {
	      List<AirportDetails> airportDetails = AirportInfoHelper.csvToAirports(file.getInputStream());
	      airportRepository.saveAll(airportDetails);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }
	  
	  public List<AirportDetails> getAllAirports() {
	    return airportRepository.findAll();
	  }
	  
	  public List<AirportDetails> findByairportCode(String airportCode) {
		    return airportRepository.findByairportCode(airportCode);
	  }
	  
	  
}




package com.maurevair.booking.web;

import com.maurevair.booking.domain.WelcomeMessage;
import com.maurevair.booking.entity.AirportDetails;
import com.maurevair.booking.entity.FlightDetails;
import com.maurevair.booking.helper.AirportInfoHelper;
import com.maurevair.booking.repository.AirportRepository;
import com.maurevair.booking.service.AirportInfoService;
import com.maurevair.booking.service.BookingDetailsService;
import com.maurevair.booking.service.FlightDetailsService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
/*@RequestMapping(path = "/api/v1/info/")*/
public class InfoController {
	
	  @Autowired
	  AirportInfoService fileService;
	  
	  @Autowired
	  FlightDetailsService flightDetailsService;
	  
	  @Autowired
	  BookingDetailsService bookingDetailsService;
	  
	  @PostMapping("/upload")
	  public ResponseEntity<WelcomeMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    if (AirportInfoHelper.hasCSVFormat(file)) {
	      try {
	        fileService.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new WelcomeMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new WelcomeMessage(message));
	      }
	    }
	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new WelcomeMessage(message));
	  }
	  
	  
	  @GetMapping("/airports")
	  public ResponseEntity<List<AirportDetails>> getAllAirports() {
	    try {
	      List<AirportDetails> airports = fileService.getAllAirports();
	      if (airports.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(airports, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/airports/{airportCode}")
	  public ResponseEntity<List<AirportDetails>> findByairportCode(@PathVariable ("airportCode") String airportCode) {
	    try {
	      List<AirportDetails> airports = fileService.findByairportCode(airportCode);
	      if (airports.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(airports, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PostMapping("/uploadFlightDetails")
	  public ResponseEntity<WelcomeMessage> uploadFlightDetails(@RequestParam("file") MultipartFile file) {
	    String message = "";

	      try {
	    	flightDetailsService.parseSdnFile(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new WelcomeMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new WelcomeMessage(message));
	      }
	      
	  }
	  
	  @GetMapping("/flights/{originanddestination}")
	  public ResponseEntity<List<FlightDetails>> findByOriginAndDestination(@RequestParam ("origin") String origin, @RequestParam ("destination") String destination) {
	    try {
	      List<FlightDetails> flights = flightDetailsService.findByOriginAndDestination(origin, destination);
	      if (flights.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(flights, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}

package com.maurevair.booking.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.maurevair.booking.entity.AirportDetails;

public class AirportInfoHelper {
	
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "country", "region", "city", "airportCode" };
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  
	  public static List<AirportDetails> csvToAirports(InputStream inputstream) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      List<AirportDetails> airportDetails = new ArrayList<AirportDetails>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      for (CSVRecord csvRecord : csvRecords) {
		    	  AirportDetails airportDetail = new AirportDetails(
		    			csvRecord.get("airportCode"),
		                csvRecord.get("country"),
		                csvRecord.get("region"),
		                csvRecord.get("city")
		              );
		    	  airportDetails.add(airportDetail);
		        }
		        return airportDetails;
		      } catch (IOException e) {
		        throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		      }
}
		  }



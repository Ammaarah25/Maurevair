package com.maurevair.booking.service;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.maurevair.booking.entity.BookingDetails;
import com.maurevair.booking.repository.BookingRepository;

@Transactional
@Service
public class BookingDetailsService {

	@Autowired
	BookingRepository bookingRepository;
	

	public void parseSdnFile(MultipartFile fileName) {    

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	        try {
	        	
	        	String file = fileName.getOriginalFilename();
	        	
	            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	            DocumentBuilder db = dbf.newDocumentBuilder();

	            Document doc = db.parse(new File(file));

	        	doc.getDocumentElement().normalize();

	            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());

	            NodeList list = doc.getElementsByTagName("BookingInfo");
	            NodeList flightList = doc.getElementsByTagName("flight");
	            
	            String flightId = null;
	           
	      for (int x = 0; x < flightList.getLength(); x++) {  
	    	  Node node = flightList.item(x);
	    	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	                 Element element = (Element) node;
	                 flightId = element.getAttributes().getNamedItem("id").getNodeValue();
	    	  }
	
	    	  for (int temp = 0; temp < list.getLength(); temp++) {	    	  

	    	  if (node.getNodeType() == Node.ELEMENT_NODE) {
	                 Element element = (Element) node;
	                 flightId = element.getAttributes().getNamedItem("id").getNodeValue();

	                 
	             Node nodeBooking = list.item(temp);
	             if (nodeBooking.getNodeType() == Node.ELEMENT_NODE) {
	                 Element elementBooking = (Element) nodeBooking;
	                 int seatsAvailableFirst = 0;
	                 int seatsAvailablePremium = 0;
	                 int seatsAvailableEconomy = 0;
	                 
	                 if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().equals("First") && !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().isEmpty())){
	                 seatsAvailableFirst = Integer.parseInt(elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
	                 System.out.println("First :" + seatsAvailableFirst);
	                 }
	                 
	                 if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().equals("PremiumEconomy") && !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().isEmpty())){
		                 seatsAvailablePremium = Integer.parseInt(elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
		                 System.out.println("Premium :" + seatsAvailablePremium);
		                }
	                 
	                 if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().equals("Economy") && !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().isEmpty())){
		                 seatsAvailableEconomy = Integer.parseInt(elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
		                 System.out.println("Economy :" + seatsAvailableEconomy);
		                }
	                 

	                 BookingDetails entity = new BookingDetails(flightId, seatsAvailableFirst, seatsAvailablePremium, seatsAvailableEconomy); 
	                 bookingRepository.save(new BookingDetails(flightId, seatsAvailableFirst, seatsAvailablePremium, seatsAvailableEconomy));


}}}}
	      
	      }catch (IOException | ParserConfigurationException | SAXException e) {
		      throw new RuntimeException("fail to store xml data: " + e.getMessage());
		    }
	        }}

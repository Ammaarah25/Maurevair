package com.maurevair.booking.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.maurevair.booking.entity.AirportDetails;
import com.maurevair.booking.entity.FlightDetails;
import com.maurevair.booking.repository.FlightRepository;

@Service
public class FlightDetailsService {

	@Autowired
	FlightRepository flightRepository;

	public void parseSdnFile(MultipartFile fileName) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			String file = fileName.getOriginalFilename();

			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(file));

			doc.getDocumentElement().normalize();

			NodeList list = doc.getElementsByTagName("flight");
			NodeList bookingList = doc.getElementsByTagName("BookingInfo");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String id = element.getAttributes().getNamedItem("id").getNodeValue();
					String carrier = element.getAttributes().getNamedItem("Carrier").getNodeValue();
					String flightNumber = element.getAttributes().getNamedItem("FlightNumber").getNodeValue();
					String origin = element.getAttributes().getNamedItem("Origin").getNodeValue();
					String destination = element.getAttributes().getNamedItem("Destination").getNodeValue();
					String departureTime = element.getAttributes().getNamedItem("DepartureTime").getNodeValue();
					String arrivalTime = element.getAttributes().getNamedItem("ArrivalTime").getNodeValue();

					for (int x = 0; x < bookingList.getLength(); x++) {
						Node nodeBooking = bookingList.item(x);
						if (nodeBooking.getNodeType() == Node.ELEMENT_NODE) {
							Element elementBooking = (Element) nodeBooking;
							int seatsAvailableFirst = 0;
							int seatsAvailablePremium = 0;
							int seatsAvailableEconomy = 0;

							if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue().equals("First")
									&& !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue()
											.isEmpty())) {
								seatsAvailableFirst = Integer.parseInt(
										elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
								System.out.println("First :" + seatsAvailableFirst);
							}

							if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue()
									.equals("PremiumEconomy")
									&& !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue()
											.isEmpty())) {
								seatsAvailablePremium = Integer.parseInt(
										elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
								System.out.println("Premium :" + seatsAvailablePremium);
							}

							if (elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue()
									.equals("Economy")
									&& !(elementBooking.getAttributes().getNamedItem("CabinClass").getNodeValue()
											.isEmpty())) {
								seatsAvailableEconomy = Integer.parseInt(
										elementBooking.getAttributes().getNamedItem("SeatsAvailable").getNodeValue());
								System.out.println("Economy :" + seatsAvailableEconomy);
							}
							FlightDetails entity = new FlightDetails(id, carrier, flightNumber, origin, destination,
									departureTime, arrivalTime, seatsAvailableFirst, seatsAvailablePremium,
									seatsAvailableEconomy);
							flightRepository.save(entity);
						}

					}
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new RuntimeException("fail to store xml data: " + e.getMessage());
		}
	}

	public List<FlightDetails> findByOriginAndDestination(String origin, String destination) {
		return flightRepository.findByOriginAndDestination(origin, destination);
	}

}

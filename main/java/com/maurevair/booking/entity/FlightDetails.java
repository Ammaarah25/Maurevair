package com.maurevair.booking.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class FlightDetails {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "carrier")
	private String carrier;
	
	@Column(name = "fnumber")
	private String flightNumber;
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "departure")
	private String departure;
	
	@Column(name = "arrival")
	private String arrival;
	
	@Column(name = "first_class")
	private int first;
	
	@Column(name = "premium")
	private int premium;
	
	@Column(name = "economy")
	private int economy;

	
	public FlightDetails() {

	}

	public FlightDetails(String id, String carrier, String flightNumber, String origin, String destination,
			String departure, String arrival) {
		super();
		this.id = id;
		this.carrier = carrier;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	

	public FlightDetails(String id, String carrier, String flightNumber, String origin, String destination,
			String departure, String arrival, int first, int premium, int economy) {
		super();
		this.id = id;
		this.carrier = carrier;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.first = first;
		this.premium = premium;
		this.economy = economy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public int getEconomy() {
		return economy;
	}

	public void setEconomy(int economy) {
		this.economy = economy;
	}
	
	
	
}

package com.maurevair.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class BookingDetails {
	
	@Id
	@Column(name = "flight_id")
	private String flightId;
	
	@Column(name = "first_class")
	private int first;
	
	@Column(name = "premium")
	private int premium;
	
	@Column(name = "economy")
	private int economy;

	public BookingDetails() {

	}
	

	public BookingDetails(String flightId, int first, int premium, int economy) {
		super();
		this.flightId = flightId;
		this.first = first;
		this.premium = premium;
		this.economy = economy;
	}
	
	

	public String getFlightId() {
		return flightId;
	}


	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}


	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getpremium() {
		return premium;
	}

	public void setpremium(int premium) {
		this.premium = premium;
	}

	public int getEconomy() {
		return economy;
	}

	public void setEconomy(int economy) {
		this.economy = economy;
	}

	
}

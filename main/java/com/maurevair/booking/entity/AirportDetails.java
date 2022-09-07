package com.maurevair.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class AirportDetails {
	
	@Id
	@Column(name = "code")
	private String airportCode;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "city")
	private String city;
	
	public AirportDetails() {
		
	}

	public AirportDetails(String airportCode, String country, String region, String city) {
		super();
		this.airportCode = airportCode;
		this.country = country;
		this.region = region;
		this.city = city;
	}



	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	
	  @Override
	  public String toString() {
	    return "AirportDetails [country=" + country + ", region=" + region + ", city=" + city + ", airportCode=" + airportCode + "]";
	  }

}

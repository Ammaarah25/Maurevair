
package com.maurevair.booking.domain;

public class WelcomeMessage {

	public WelcomeMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
	    this.message = message;
	  }

	private String message;
}

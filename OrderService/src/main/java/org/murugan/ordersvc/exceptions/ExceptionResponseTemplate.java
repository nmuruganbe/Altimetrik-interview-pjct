/**
 * 
 */
package org.murugan.ordersvc.exceptions;

import java.util.Date;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Murugan Nagarajan
 *
 */
public class ExceptionResponseTemplate extends ResponseEntityExceptionHandler {

	private String message;
	private String details;
	private Date timeOfOccurence;

	public ExceptionResponseTemplate(String message, String details, Date timeOfOccurence) {
		super();
		this.message = message;
		this.details = details;
		this.timeOfOccurence = timeOfOccurence;
	}

	public ExceptionResponseTemplate() {
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public Date getTimeOfOccurence() {
		return timeOfOccurence;
	}

}

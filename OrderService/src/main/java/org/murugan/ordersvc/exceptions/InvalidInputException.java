/**
 * 
 */
package org.murugan.ordersvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Murugan Nagarajan
 *
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045047870357315918L;

	public InvalidInputException(String message) {
		super(String.format("Input provided is invalid.  %s", message));
	}

}

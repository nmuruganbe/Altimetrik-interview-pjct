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

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045047870357315918L;

	public OrderNotFoundException(String message) {
		super(String.format("Order with ID %s is not found", message));
	}

}

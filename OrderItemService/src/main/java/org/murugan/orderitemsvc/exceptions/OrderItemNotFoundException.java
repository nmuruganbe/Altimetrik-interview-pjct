package org.murugan.orderitemsvc.exceptions;
/**
 * 
 */


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Murugan Nagarajan
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045047870357315918L;

	public OrderItemNotFoundException(String message) {
		super(String.format("OrderItem with ID %s is not found", message));
	}

}

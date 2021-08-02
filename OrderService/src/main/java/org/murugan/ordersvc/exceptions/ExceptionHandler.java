/**
 * 
 */
package org.murugan.ordersvc.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customized exception handler for handling all the exceptions and returns
 * meaningful messages to the clients.
 * 
 * @author Murugan Nagarajan
 *
 */

@RestControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * To handle {@link OrderNotFoundException}
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<ExceptionResponseTemplate> handleOrderNotFoundException(Exception ex,
			WebRequest request) throws Exception {
		ExceptionResponseTemplate exceptionTemplate = new ExceptionResponseTemplate(ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<ExceptionResponseTemplate>(exceptionTemplate, HttpStatus.NOT_FOUND);

	}

	/**
	 * To handle {@link InvalidInputException}
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<ExceptionResponseTemplate> handleInvalidInputException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponseTemplate exceptionTemplate = new ExceptionResponseTemplate(ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<ExceptionResponseTemplate>(exceptionTemplate, HttpStatus.BAD_REQUEST);

	}

	/**
	 * To handle rest of the {@link Exception}
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponseTemplate> handleAllExceptions(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponseTemplate exceptionTemplate = new ExceptionResponseTemplate(ex.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<ExceptionResponseTemplate>(exceptionTemplate, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

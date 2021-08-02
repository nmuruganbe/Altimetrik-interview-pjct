/**
 * 
 */
package org.murugan.ordersvc.util;

import java.util.stream.Stream;

/**
 * @author Murugan Nagarajan
 *
 */
public class OrderConstants {

	public static final Stream<String> orderStatus = Stream.of("Submitted", "Pending", "Delivered", "Cancelled");

}

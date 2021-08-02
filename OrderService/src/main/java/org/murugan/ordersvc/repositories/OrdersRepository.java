/**
 * 
 */
package org.murugan.ordersvc.repositories;

import org.murugan.ordersvc.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murugan Nagarajan
 *
 */

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	

}

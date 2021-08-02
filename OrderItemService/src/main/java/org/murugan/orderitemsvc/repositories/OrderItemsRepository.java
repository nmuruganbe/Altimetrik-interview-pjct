/**
 * 
 */
package org.murugan.orderitemsvc.repositories;

import java.util.List;

import org.murugan.orderitemsvc.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murugan Nagarajan
 *
 */
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

//	List<OrderItems> findByOrder(Long orderId);

	List<OrderItems> findByOrderId(Long orderId);
}

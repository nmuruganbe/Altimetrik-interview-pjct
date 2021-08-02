/**
 * 
 */
package org.murugan.orderitemsvc.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.murugan.orderitemsvc.exceptions.InvalidInputException;
import org.murugan.orderitemsvc.models.OrderItems;
import org.murugan.orderitemsvc.models.OrderItemsList;
import org.murugan.orderitemsvc.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Murugan Nagarajan
 *
 */

@RestController
public class OrderItemsController {

	@Autowired
	private OrderItemsRepository orderItemsRepo;

	/**
	 * To retrieve all the items from table
	 * @return
	 */
	@GetMapping(path = "/items")
	public List<OrderItems> getAllOrderItems() {
		return orderItemsRepo.findAll();
	}

	/**
	 * To fetch items related to a particular Order using Order Id
	 * @param orderId
	 * @return
	 */
	@GetMapping(path = "/items/{orderId}")
	public OrderItemsList getOrderItemsByOrderId(@PathVariable Long orderId) {
		OrderItemsList orderItemList = new OrderItemsList();
		orderItemList.setOrderItemsList(orderItemsRepo.findByOrderId(orderId));
		return orderItemList;
	}

	/**
	 * To update or soft delete a particular item with its id
	 * @param id
	 * @param orderItem
	 * @param result
	 * @return
	 */
	@PutMapping("/items/{id}")
	public OrderItems modifyOrderItemById(@Valid @PathVariable Integer id, @RequestBody OrderItems orderItem,
			BindingResult result) {
		if (result.hasFieldErrors()) {
			throw new InvalidInputException(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList()).toString());
		}

		orderItem.setOrderItemId(id);
		return orderItemsRepo.save(orderItem);
	}

	/**
	 * This method is to create a new item
	 * @param orderItem
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/items")
	public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderItems orderItem, BindingResult result) {

		if (result.hasFieldErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList()).toString());
		}

		OrderItems createdItem = orderItemsRepo.save(orderItem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdItem.getOrderItemId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}

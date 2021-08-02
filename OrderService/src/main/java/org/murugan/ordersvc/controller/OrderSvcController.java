/**
 * 
 */
package org.murugan.ordersvc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.murugan.ordersvc.exceptions.InvalidInputException;
import org.murugan.ordersvc.exceptions.OrderNotFoundException;
import org.murugan.ordersvc.models.OrderItems;
import org.murugan.ordersvc.models.OrderItemsList;
import org.murugan.ordersvc.models.OrderRequest;
import org.murugan.ordersvc.models.Orders;
import org.murugan.ordersvc.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Murugan Nagarajan
 *
 */

@RestController
public class OrderSvcController {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * To retrieve all the Orders from the table
	 * @return
	 */
	@GetMapping(path = "/orders")
	public List<OrderRequest> getAllOrderItems() {
		List<Orders> orders = ordersRepo.findAll();
		List<OrderRequest> orderRequestList = new ArrayList<>();
		for (Orders order : orders) {
			orderRequestList.add(getOrderDetails(order.getOrderId()));
		}
		return orderRequestList;
	}

	/**
	 * Method to retrieve individual order with Order ID
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/orders/{id}")
	public EntityModel<OrderRequest> getOrderById(@PathVariable Long id) {
		OrderRequest orderResp = getOrderDetails(id);
		EntityModel<OrderRequest> resource = EntityModel.of(orderResp);
		WebMvcLinkBuilder link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrderItems());
		return resource.add(link.withRel("all-Orders"));
	}

	/**
	 * To retrieve order details from DB
	 * @param id
	 * @return
	 */
	private OrderRequest getOrderDetails(Long id) {
		Optional<Orders> order = ordersRepo.findById(id);
		if (order.isEmpty())
			throw new OrderNotFoundException(id.toString());// customized exception
		OrderItemsList orderIntemsList = restTemplate.getForObject("http://order-item-service/items/" + id,
				OrderItemsList.class);
		OrderRequest orderResp = getOrderResponse(order.get());
		orderResp.setOrderItems(orderIntemsList.getOrderItemsList());
		orderResp.setTotalCost(
				orderResp.getOrderItems().stream().map(e -> e.getPrice() * e.getQuantity()).reduce(0, Integer::sum));
		return orderResp;
	}

	/**
	 * To populate the response bean from order bean
	 * @param order
	 * @return
	 */
	private OrderRequest getOrderResponse(Orders order) {
		OrderRequest orderResponse = new OrderRequest();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setCustomerName(order.getCustomerName());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setShippingAddress(order.getShippingAddress());
		orderResponse.setOrderStatus(order.getOrderStatus());
		return orderResponse;
	}
	
	/**
	 * Method  to update or soft delete any order if required
	 * @param id
	 * @param orderInput
	 * @param result
	 * @return
	 */
	@PutMapping(path = "/orders/{id}")
	public Orders modifyOrderById(@Valid @PathVariable Long id, @RequestBody OrderRequest orderInput,
			BindingResult result) {

		if (result.hasFieldErrors()) {
			throw new InvalidInputException(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList()).toString());
		}

		Optional<Orders> order = ordersRepo.findById(id);
		if (order.isEmpty())
			throw new OrderNotFoundException(id.toString());
		Orders orderUpdated = getOrder(orderInput, order.get());
		orderUpdated = ordersRepo.save(orderUpdated);
		for (OrderItems orderItem : orderInput.getOrderItems()) {
			orderItem.setOrderId(id);

			restTemplate.put("http://order-item-service/items/" + orderItem.getOrderItemId(), orderItem);
			/*
			 * restTemplate.patchForObject("http://localhost:9091/items/" +
			 * orderItem.getOrderItemId(), orderItem, OrderItems.class);
			 */
		}
		return orderUpdated;
	}

	/**
	 * Method  to create orders as required
	 * @param orderInput
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/orders")
	public ResponseEntity<String> createOrderItem(@Valid @RequestBody OrderRequest orderInput, BindingResult result) {

		if (result.hasFieldErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList()).toString());
		}

		Orders order = new Orders();
		order = getOrder(orderInput, order);
		List<OrderItems> orderItems = orderInput.getOrderItems();
		Orders createdItem = ordersRepo.save(order);
		for (OrderItems orderItem : orderItems) {
			orderItem.setOrderId(createdItem.getOrderId());
			restTemplate.postForObject("http://order-item-service/items", orderItem, OrderItems.class);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdItem.getOrderId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * To populate Order bean from order input bean for saving to DB
	 * @param orderInput
	 * @param order
	 * @return
	 */
	private Orders getOrder(OrderRequest orderInput, Orders order) {

		order.setCustomerName(orderInput.getCustomerName());
		order.setOrderDate(orderInput.getOrderDate());
		order.setShippingAddress(orderInput.getShippingAddress());
		order.setTotalCost(
				orderInput.getOrderItems().stream().map(e -> e.getPrice() * e.getQuantity()).reduce(0, Integer::sum));
		order.setOrderStatus(orderInput.getOrderStatus());
		return order;
	}

}

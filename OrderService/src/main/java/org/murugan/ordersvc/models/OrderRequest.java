/**
 * 
 */
package org.murugan.ordersvc.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Murugan Nagarajan
 *
 */

public class OrderRequest {
//	@Digits(integer = 10, fraction = 0, message = "valid number please")
	private Long orderId;
	@Size(min = 4, max = 30, message = "Customer Name should be 4-50 characters")
	private String customerName;
	private Date orderDate;
	@NotBlank(message = "Shipping address can't be empty")
	private String shippingAddress;
	private List<OrderItems> orderItems;
	private Integer totalCost;
	@NotBlank(message = "Order status can't be empty")
	private String orderStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderRequest [orderId=" + orderId + ", customerName=" + customerName + ", orderDate=" + orderDate
				+ ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems + ", totalCost=" + totalCost
				+ ", orderStatus=" + orderStatus + "]";
	}

}

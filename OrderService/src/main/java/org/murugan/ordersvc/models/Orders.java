/**
 * 
 */
package org.murugan.ordersvc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Murugan Nagarajan
 *
 */
@Entity
@Table(name = "ORDERS")
public class Orders {

	@Id
	@GeneratedValue(generator = "order_seq")
	@SequenceGenerator(name = "order_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	@Column(name = "SHIPPING_ADDRESS")
	private String shippingAddress;
	@Column(name = "TOTAL_COST")
	private Integer totalCost;
	@Column(name = "ORDER_STATUS")
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
		return "Orders [orderId=" + orderId + ", customerName=" + customerName + ", orderDate=" + orderDate
				+ ", shippingAddress=" + shippingAddress + ", totalCost=" + totalCost + ", orderStatus=" + orderStatus
				+ "]";
	}

}

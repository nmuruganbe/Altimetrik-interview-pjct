/**
 * 
 */
package org.murugan.orderitemsvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Murugan Nagarajan
 *
 */
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItems {

	@Id
	@GeneratedValue(generator = "order_item_seq")
	@SequenceGenerator(name = "order_item_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "ORDER_ITEM_ID")
	private Integer orderItemId;
	@Column(name = "PRODUCT_CODE")
	private Integer productCode;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "QUANTITY")
	private Integer quantity;
	@Column(name = "PRICE")
	private Integer price;
//	@NotBlank(message = "Order Id can't be blank")
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "ACTIVE_IN_ORDER")
	private char active;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "OrderItems [orderItemId=" + orderItemId + ", productCode=" + productCode + ", productName="
				+ productName + ", quantity=" + quantity + ", price=" + price + ", orderId=" + orderId + ", active="
				+ active + "]";
	}

}

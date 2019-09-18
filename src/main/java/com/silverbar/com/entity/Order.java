/*
 * representation for order
 */

package com.silverbar.com.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.silverbar.com.constants.OrderType;

@Entity
@Table(name="the_order")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="user_Id")
	String userId;
	@Column(name="quantity")
	BigDecimal quantity;
	@Column(name="price")
	BigDecimal pricePerKg;
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	OrderType orderType;

	public Order() {

	}

	public Order(String userId, BigDecimal quantity, BigDecimal pricePerKg, OrderType orderType) {
		this.userId = userId;
		this.quantity = quantity;
		this.pricePerKg = pricePerKg;
		this.orderType = orderType;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Order order = (Order) obj;

		return (this.userId == order.userId && this.pricePerKg == order.pricePerKg && this.quantity == order.quantity
				&& this.orderType == order.orderType);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(BigDecimal pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", quantity=" + quantity + ", pricePerKg=" + pricePerKg + ", orderType="
				+ orderType + "]";
	}

}

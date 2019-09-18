/*
 * use to group the orders
 */
package com.silverbar.com.grouping;

import java.math.BigDecimal;

public class OrderGroup {
	BigDecimal pricePerKg;
	BigDecimal quantity;

	public OrderGroup(BigDecimal pricePerKg, BigDecimal quantity) {

		this.pricePerKg = pricePerKg;
		this.quantity = quantity;
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

		OrderGroup orderGroup = (OrderGroup) obj;

		return (this.pricePerKg == orderGroup.pricePerKg && this.quantity == orderGroup.quantity);
	}

	public BigDecimal getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(BigDecimal pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderGroup [pricePerKg=" + pricePerKg + ", quantity=" + quantity + "]";
	}

}

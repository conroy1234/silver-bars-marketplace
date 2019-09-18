
/*
 * this class is use to test the application similar to the implementation in main
 * the difference is 
 * main is populated via h2 database
 */

package com.silverbar.com.factory;

import java.math.BigDecimal;

import com.silverbar.com.constants.OrderType;
import com.silverbar.com.entity.Order;
import com.silverbar.com.grouping.OrderBoard;
import com.silverbar.com.grouping.OrderGroup;

import javaslang.collection.List;
import javaslang.collection.Seq;

public class OrderSummaryFactory {

	public Seq<OrderGroup> summaryTestCase(OrderType orderType, Seq<OrderGroup> expectedSummary) {
		// sample orders from the exercise description

		OrderBoard orderBoard = registerListOfOrders(
				getOrder("user1", BigDecimal.valueOf(9), new BigDecimal("306"), orderType),
				getOrder("user2", BigDecimal.valueOf(7), new BigDecimal("310"), orderType),
				getOrder("user3", BigDecimal.valueOf(4), new BigDecimal("307"), orderType),
				getOrder("user4", BigDecimal.valueOf(2), new BigDecimal("306"), orderType));

		Seq<OrderGroup> summary = Summarizer.summarize(orderBoard, orderType);
		return summary;

	}

	public Order getOrder(String userId, BigDecimal quantity, BigDecimal pricePerKg, OrderType orderType) {
		return new Order(userId, quantity, pricePerKg, orderType);
	}

	public OrderGroup getOrderGroupes(BigDecimal pricePerKg, BigDecimal quantity) {
		return new OrderGroup(pricePerKg, quantity);
	}

	private OrderBoard registerListOfOrders(Order a, Order b, Order c, Order d) {
		List<Order> orderList = List.of(a, b, c, d);
		OrderBoard orderBoard = new OrderBoard();
		orderBoard.register(orderList);
		
		return orderBoard;
	}

}


/*
 * the mane method used to start uo the application
 * 1 you can run this class as spring boot application this can be run in a web browser once started
 * 2 the application will be started on port 9090
 * http://localhost:9090/h2
 * url is :  jdbc:h2:mem:order
 * username is : sa
 * don't need a password
 * I also print out the result in the console for logging purpose
 * 
 */
package com.silverbar.com;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silverbar.com.constants.OrderType;
import com.silverbar.com.entity.Order;
import com.silverbar.com.factory.OrderRepository;
import com.silverbar.com.factory.Summarizer;
import com.silverbar.com.grouping.OrderBoard;
import com.silverbar.com.grouping.OrderGroup;

import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.collection.Stream;

@SpringBootApplication
public class SilverBarsMarketplaceApplication implements CommandLineRunner {
	@Autowired 
	OrderRepository orderRepository;
	
	OrderBoard orderBoard; 
	
	public static void main(String[] args) {
		SpringApplication.run(SilverBarsMarketplaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// a summary of all the orders buy
		Seq<OrderGroup> expectedSummary = Stream.of(
				getOrderGroupes(BigDecimal.valueOf(306), BigDecimal.valueOf(9)),
				getOrderGroupes(BigDecimal.valueOf(307), BigDecimal.valueOf(7)),
				getOrderGroupes(BigDecimal.valueOf(310), BigDecimal.valueOf(4)));
		// buy order
		summaryTestCase(OrderType.BUY, expectedSummary);
		//sell oredr represent removed
		summaryTestCase(OrderType.SELL, expectedSummary);
	}

	public Seq<OrderGroup> summaryTestCase(OrderType orderType, Seq<OrderGroup> expectedSummary) {
		// sample orders from the exercise description
		Iterable<Order>orersResult = orderRepository.findAll();
		
		for(Order order:orersResult) {
			System.out.println("order from database "+order);
		this.orderBoard = registerListOfOrders(order);
		}

		Seq<OrderGroup> summary = Summarizer.summarize(orderBoard, orderType);
		
		return summary;

	}

	public Order getOrder(String userId, BigDecimal quantity, BigDecimal pricePerKg, OrderType orderType) {
		return new Order(userId, quantity, pricePerKg, orderType);
	}

	public OrderGroup getOrderGroupes(BigDecimal pricePerKg, BigDecimal quantity) {
		return new OrderGroup(pricePerKg, quantity);
	}

	private OrderBoard registerListOfOrders(Order order) {

		List<Order> orderList = List.of(order);

		OrderBoard orderBoard = new OrderBoard();
		orderBoard.register(orderList);

		return orderBoard;
	}
}

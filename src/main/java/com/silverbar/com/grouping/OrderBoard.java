package com.silverbar.com.grouping;

import static java.util.function.Predicate.isEqual;

import org.springframework.beans.factory.annotation.Autowired;

import com.silverbar.com.entity.Order;
import com.silverbar.com.factory.OrderRepository;

import javaslang.collection.List;

/**
 * Requirements 1. and 2.
 * 1. Register an order
 * 2. Cancel an order
 * Implemented as a persistent data structure
 * @see <a href="https://en.wikipedia.org/wiki/Persistent_data_structure"/>
 */
public class OrderBoard {

	@Autowired
	OrderRepository orderRepository;
	
    private final List<Order> orders;

    public OrderBoard() {
        this(List.empty());
    }

    private OrderBoard(List<Order> orders) {
        this.orders = orders;
    }

    public OrderBoard register(Order order) {    	
    	System.out.println(order);
        return new OrderBoard(orders.prepend(order));
    }

    public OrderBoard register(List<Order> orders) {
    	
    	
        return orders.foldLeft(this, OrderBoard::register);
    }

    public OrderBoard cancel(Order order) {
        return new OrderBoard(orders.filter(isEqual(order).negate()));
    }

    public List<Order> orders() {
        return orders.reverse();
    }
}

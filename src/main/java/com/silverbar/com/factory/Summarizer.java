package com.silverbar.com.factory;

import javaslang.collection.Seq;
import javaslang.collection.Stream;

import java.math.BigDecimal;
import java.util.Comparator;

import com.silverbar.com.constants.OrderType;
import com.silverbar.com.entity.Order;
import com.silverbar.com.grouping.OrderBoard;
import com.silverbar.com.grouping.OrderGroup;

import static java.util.Comparator.comparing;


/**
 * Requirement 3.
 * Summarize an order.
 */
public class Summarizer {

   public static Seq<OrderGroup> summarize(OrderBoard orderBoard, OrderType orderType) {
        return orderBoard.orders().
                groupBy(Order::getPricePerKg).
                map(keyValuePair -> new OrderGroup(keyValuePair._1, sumQuantity(keyValuePair._2))).
                sorted(accordingTo(orderType));
    }

    private static BigDecimal sumQuantity(Seq<Order> orders) {
        return orders.map(Order::getQuantity).foldLeft(BigDecimal.valueOf(0), BigDecimal::add);
    }

    private static Comparator<OrderGroup> accordingTo(OrderType orderType) {
        return orderType == OrderType.SELL ?
                comparing(OrderGroup::getPricePerKg) :
                comparing(OrderGroup::getPricePerKg).reversed();
    }
    
   
}

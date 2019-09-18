package com.silverbar.com.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.silverbar.com.constants.OrderType;
import com.silverbar.com.grouping.OrderGroup;

import javaslang.collection.Seq;
import javaslang.collection.Stream;

public class SummarizerTest {
	private OrderSummaryFactory factorySummary = new OrderSummaryFactory();
//
	
	@Test
	public void OrderTypeSellTestCase() {
		//check for all orders
		Seq<OrderGroup> expectedSummary = Stream.of(
				factorySummary.getOrderGroupes(BigDecimal.valueOf(306), BigDecimal.valueOf(9)),
				factorySummary.getOrderGroupes(BigDecimal.valueOf(307), BigDecimal.valueOf(7)),
				factorySummary.getOrderGroupes(BigDecimal.valueOf(310), BigDecimal.valueOf(4)));

		
		factorySummary.summaryTestCase(OrderType.SELL, expectedSummary);		
		assertThat(expectedSummary.contains(new OrderGroup(BigDecimal.valueOf(306), BigDecimal.valueOf(5.5))));
		assertThat(new OrderGroup(BigDecimal.valueOf(306), BigDecimal.valueOf(5.5)).getPricePerKg(),is(BigDecimal.valueOf(306)));
	}

	@Test
	public void OrderTypeBuyTestCase() {
		Stream<OrderGroup> expectedSummary = Stream.of(
				factorySummary.getOrderGroupes(BigDecimal.valueOf(306), BigDecimal.valueOf(4)),
				factorySummary.getOrderGroupes(BigDecimal.valueOf(307), BigDecimal.valueOf(7)),
				factorySummary.getOrderGroupes(BigDecimal.valueOf(310), BigDecimal.valueOf(9)));


		// and that buy order are the same except with reverse ordering
		factorySummary.summaryTestCase(OrderType.BUY, expectedSummary.reverse());				
		assertThat(expectedSummary.contains(new OrderGroup(BigDecimal.valueOf(306), BigDecimal.valueOf(9))));
		assertThat(new OrderGroup(BigDecimal.valueOf(306), BigDecimal.valueOf(5.5)).getPricePerKg(),is(BigDecimal.valueOf(306)));

		
	}

}
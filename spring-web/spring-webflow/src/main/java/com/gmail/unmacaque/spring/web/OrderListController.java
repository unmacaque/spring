package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Order;
import com.gmail.unmacaque.spring.domain.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Controller
public class OrderListController {

	private static final String ORDERS_MODEL = "orders";

	private final OrderService orderService;

	public OrderListController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/list")
	public String list() {
		return "list";
	}

	@ModelAttribute(ORDERS_MODEL)
	public Map<Long, Order> orders() {
		return orderService.getOrders();
	}

}

package com.gmail.unmacaque.spring.webflow;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.unmacaque.spring.webflow.order.Order;
import com.gmail.unmacaque.spring.webflow.order.OrderService;

@Controller
@RequestMapping("/list")
public class OrderListController {

	private static final String ORDERS_MODEL = "orders";

	private final OrderService orderService;

	@Autowired
	public OrderListController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public String list() {
		return "list";
	}

	@ModelAttribute(ORDERS_MODEL)
	public Map<Long, Order> orders() {
		return orderService.getOrders();
	}

}

package com.gmail.unmacaque.spring.webflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gmail.unmacaque.spring.webflow.order.OrderService;

@Controller
@RequestMapping(value = "/")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("orders", orderService.getOrders());
		return "list";
	}
}

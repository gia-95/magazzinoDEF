package it.TNetwork.magazzino.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.service.IOrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	@Qualifier("mainOrderService")
	private IOrderService orderService;

	@PostMapping(value = "/", produces = "application/json")
	public Order insert(@RequestBody Order ordine) {

		return this.orderService.insert(ordine);
	}

	@GetMapping(value = "/")
	public List<Order> getAll() {

		List<Order> ordini = this.orderService.getAll();
		return ordini;
	}

	@GetMapping(value = "/{orderNumber}", produces = "application/json")
	public List<Order> getByNConsegna(@PathVariable int orderNumber) {

		return this.orderService.getByOrderNumber(orderNumber);
	}
	

}

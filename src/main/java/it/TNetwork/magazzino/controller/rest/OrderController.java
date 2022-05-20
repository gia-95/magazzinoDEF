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
import it.TNetwork.magazzino.model.response.BaseResponse;
import it.TNetwork.magazzino.service.IOrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	@Qualifier("mainOrderService")
	private IOrderService orderService;

	@PostMapping(value = "/addOrder", produces = "application/json")
	public BaseResponse insert(@RequestBody Order ordine) {

		return this.orderService.insert(ordine);
	}

	@GetMapping(value = "/getOrders")
	public BaseResponse getAll() {

		return this.orderService.getAll();
	}

	@GetMapping(value = "/{numeroOrdine}", produces = "application/json")
	public BaseResponse getByNumeroOrdine(@PathVariable int numeroOrdine) {

		return this.orderService.getByOrderNumber(numeroOrdine);
	}

}

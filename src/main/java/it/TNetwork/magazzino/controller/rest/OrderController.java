package it.TNetwork.magazzino.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;
import it.TNetwork.magazzino.service.IOrderService;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrderController {

	@Autowired
	@Qualifier("mainOrderService")
	private IOrderService orderService;

	@PostMapping(value = "/addOrder", produces = "application/json")
	public BaseResponse insert(@RequestHeader("userId") String userId, @RequestBody Order ordine) {

		return this.orderService.insert(ordine);
	}

	@GetMapping(value = "/getOrders", produces = "application/json")
	public BaseResponse getAll(@RequestHeader("userId") String userId) {

		return this.orderService.getAll();
	}

	@GetMapping(value = "/{numeroOrdine}", produces = "application/json")
	public BaseResponse getByNumeroOrdine(@PathVariable int numeroOrdine) {

		return this.orderService.getByOrderNumber(numeroOrdine);
	}

	@GetMapping(value = "id/{idOrdine}", produces = "application/json")
	public BaseResponse getByOrderID(@PathVariable String idOrdine) {

		return this.orderService.getOrderById(idOrdine);
	}

	@DeleteMapping(value = "/{idOrder}", produces = "application/json")
	public BaseResponse remove(@RequestHeader("userId") String userId ,@PathVariable String idOrder) {

		return this.orderService.removeById(idOrder);
	}

	@PutMapping(value = "/updateOrder", produces = "application/json")
	public BaseResponse updateOrder(@RequestHeader("userId") String userId ,@RequestBody Order orderToUpdate) {

		return this.orderService.updateOrder(orderToUpdate);
	}
	
	@GetMapping(value = "/withoutDelivery", produces = "application/json")
	public BaseResponse getOrderWithoutDelivery (@RequestHeader("userId") String userId) {

		return this.orderService.getOrderWithoutDelivery();
	}
	
	

	
	

}

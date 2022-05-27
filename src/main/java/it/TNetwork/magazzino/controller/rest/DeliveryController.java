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
import it.TNetwork.magazzino.service.IDeliveryService;

@RestController
@RequestMapping("/delivery")
@CrossOrigin
public class DeliveryController {

	@Autowired
	@Qualifier("mainDeliveryService")
	private IDeliveryService deliveryService;

	// se aggiungo ( produces = "applications/json") --> mi da ERROR 500 se faccio
	// la query )
	@PostMapping(value = "/addDelivery")
	public BaseResponse insert(@RequestHeader("userId") String userId , @RequestBody Delivery delivery) {

		return this.deliveryService.insert(delivery);
	}

	// se aggiungo ( produces = "applications/json") --> mi da ERROR 500 se faccio
	// la query )
	@GetMapping(value = "/getDelivery")
	public BaseResponse getAll(@RequestHeader("userId") String userId) {

		return this.deliveryService.getAll();
	}

	@GetMapping(value = "/{idDelivery}/orders")
	public BaseResponse getOrders(@RequestHeader("userId") String userId ,@PathVariable String idDelivery) {

		return this.deliveryService.getOrders(idDelivery);
	}

	@GetMapping(value = "/{deliveryNumber}", produces = "application/json")
	public BaseResponse getByDeliveryNumber(@RequestHeader("userId") String userId ,@PathVariable String deliveryNumber) {

		return this.deliveryService.getByDeliveryNumber(deliveryNumber);
	}

	
	@GetMapping(value = "/id/{deliveryId}", produces = "application/json")
	public BaseResponse getByDeliveryId(@RequestHeader("userId") String userId ,@PathVariable String deliveryId) {

		return this.deliveryService.getByDeliveryID(deliveryId);
	}
	

	@DeleteMapping(value = "/deleteById", produces = "application/json")
	public BaseResponse remove(@RequestHeader("userId") String userId , @RequestBody String idDelivery) {

		return this.deliveryService.remove(idDelivery);
	}
	
	@PutMapping(value = "/removeOrder/{idDelivery}", produces = "application/json")
	public BaseResponse removeOrder(@RequestHeader("userId") String userId ,@PathVariable String idDelivery, @RequestBody Order order) {
		
		return this.deliveryService.removeOrder(idDelivery, order);
	}
	
	
	@PutMapping(value = "/updateDelivery", produces = "application/json")
	public BaseResponse updateDelivery(@RequestHeader("userId") String userId , @RequestBody Delivery delivery) {
		
		return this.deliveryService.insert(delivery);
	}
	
	

}

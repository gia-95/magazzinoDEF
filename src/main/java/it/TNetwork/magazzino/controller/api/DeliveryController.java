package it.TNetwork.magazzino.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.service.IDeliveryService;

@RestController
@RequestMapping("/delivery")
@CrossOrigin
public class DeliveryController {
	
	@Autowired
	@Qualifier ("mainDeliveryService")
	private IDeliveryService deliveryService;
	
	
	//se aggiungo ( produces = "applications/json") --> mi da ERROR 500 se faccio la query )
	@PostMapping (value = "/")
	public Delivery insert ( @RequestBody Delivery delivery ) {
		
		return this.deliveryService.insert(delivery);
	}
	
	//se aggiungo ( produces = "applications/json") --> mi da ERROR 500 se faccio la query )
	@GetMapping (value = "/")
	public List<Delivery> getAll () {

		return this.deliveryService.getAll();
	}
	
	@GetMapping (value = "/order" )
	public List<Order> getOrders ( @RequestBody String idDelivery) {
		
		return this.deliveryService.getOrders(idDelivery);
	}
	
	@GetMapping(value = "/{deliveryNumber}", produces = "application/json")
	public Delivery getByDeliveryNumber(@PathVariable String deliveryNumber) {

		return this.deliveryService.getByDeliveryNumber(deliveryNumber);
	}
	
	
	@DeleteMapping (value = "/")
	public Delivery remove (@RequestBody String idDelivery) {
		
		return this.deliveryService.remove (idDelivery);
	}
	
	
	
	//prova per JWT
	@GetMapping("API")
	public String provaAPI() {	
		return "API test";
	}
	

}

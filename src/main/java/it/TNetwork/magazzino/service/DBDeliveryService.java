package it.TNetwork.magazzino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.repository.DeliveryRepository;

@Service("mainDeliveryService")
public class DBDeliveryService implements IDeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;

	@Override
	public Delivery insert(Delivery delivery) {

		return this.deliveryRepo.save(delivery);
	}

	@Override
	public List<Delivery> getAll() {
		
		System.out.println("Sto nel - DBDeliveryService - @PosMapping --> getAll()");
		
		List<Delivery>  temp = this.deliveryRepo.findAll();
		
		System.out.println("temp" +temp);
		
		return temp;
	}

	@Override
	public Delivery getByDeliveryNumber(String deliveryNumber) {
		
		Delivery temp =  this.deliveryRepo.getByDeliveryNumber(deliveryNumber);
		
		return temp;
	}
	

	@Override
	public List<Order> getOrders(String idDelivery) {
		
//		Delivery delivery = this.deliveryRepo.findById(idDelivery).get();

	return null;	
	}

	@Override
	public Delivery remove(String idDelivery) {
		
		Optional<Delivery> temp = deliveryRepo.findById(idDelivery);
		
		Delivery deliveryToDelete = temp.get();
		
		if ( deliveryToDelete != null ) {
			deliveryRepo.deleteById(idDelivery);
			return deliveryToDelete;
		}
		
		return null;
	}



}

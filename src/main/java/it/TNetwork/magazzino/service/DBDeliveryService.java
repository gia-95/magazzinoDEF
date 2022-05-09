package it.TNetwork.magazzino.service;

import java.util.List;

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
		
		return this.deliveryRepo.findAll();
	}

	@Override
	public Delivery getByDeliveryNumber(String deliveryNumber) {
		
		return this.deliveryRepo.getByDeliveryNumber(deliveryNumber);
	}
	

	@Override
	public List<Order> getOrders(Delivery delivery) {
		
		return delivery.getOrdiniAssociati();
	}

	@Override
	public Delivery remove(Delivery delivery) {
		
		Delivery deletedDelivery = this.deliveryRepo.getByDeliveryNumber(delivery.getnDelivery());

		
		if ( deletedDelivery != null ) {
			this.deliveryRepo.delete(delivery);
			return deletedDelivery;
		}
		
		return null;
		
	}

}

package it.TNetwork.magazzino.service;

import java.util.List;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;

public interface IDeliveryService  {
	
	public Delivery insert(Delivery delivery);

	public List<Delivery> getAll();

	public Delivery getByDeliveryNumber(String deliveryNumber);
	
	public List<Order> getOrders( Delivery delivery);

	public Delivery remove(Delivery delivery);

}

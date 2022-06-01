package it.TNetwork.magazzino.service;

import java.util.List;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;

public interface IDeliveryService  {
	
	public BaseResponse insert(Delivery delivery);

	public BaseResponse getAll();

	public BaseResponse getByDeliveryNumber(String deliveryNumber);
	
	public BaseResponse getOrders( String idDelivery);

	public BaseResponse remove(String idDelivery);

	public BaseResponse getByDeliveryID(String deliveryID);

	public BaseResponse removeOrder(String idDelivery, Order order);

	public BaseResponse addOrderToDelivery(String idDelivery, Order order);

}

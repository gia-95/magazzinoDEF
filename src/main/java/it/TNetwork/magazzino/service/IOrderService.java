package it.TNetwork.magazzino.service;

import java.util.List;

import it.TNetwork.magazzino.model.Order;

public interface IOrderService {

	public Order insert(Order order);

	public List<Order> getAll();

	public List<Order> getByOrderNumber(int orderNumber);

}

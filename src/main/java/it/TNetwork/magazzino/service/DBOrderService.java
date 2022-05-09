package it.TNetwork.magazzino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.repository.OrderRepository;

@Service("mainOrderService")
public class DBOrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Order insert(Order order) {

		return this.orderRepo.save(order);
	}

	@Override
	public List<Order> getAll() {
		
		return this.orderRepo.findAll();
	}

	@Override
	public List<Order> getByOrderNumber(int orderNumber) {

		return this.orderRepo.getByOrderNumber(orderNumber);
	}

}

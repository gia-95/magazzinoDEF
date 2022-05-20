package it.TNetwork.magazzino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;
import it.TNetwork.magazzino.repository.OrderRepository;

@Service("mainOrderService")
public class DBOrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public BaseResponse insert(Order order) {

		BaseResponse response = null;

		Order orderSaved = this.orderRepo.save(order);

		if (orderSaved != null) {
			response = new BaseResponse(200, orderSaved, "Inserimento avvenuto correttamente");
		} else {
			// crea l'oggetto errore da mandare - settalo nella BaseResponse
		}

		return response;
	}

	@Override
	public BaseResponse getAll() {

		return new BaseResponse(200, this.orderRepo.findAll(), "Ordini trovati");
	}

	@Override
	public BaseResponse getByOrderNumber(int orderNumber) {

		List<Order> orders = this.orderRepo.getByOrderNumber(orderNumber);

		return new BaseResponse(200, orders, "Ordini trovati con 'numeroOrdine' specificato.");
	}

}

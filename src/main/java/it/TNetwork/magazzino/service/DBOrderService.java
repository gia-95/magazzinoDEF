package it.TNetwork.magazzino.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;
import it.TNetwork.magazzino.repository.OrderRepository;

@Service("mainOrderService")
public class DBOrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public BaseResponse insert(Order order) {

		Order orderSaved = this.orderRepo.save(order);

		if (orderSaved != null) {

			return new BaseResponse(200, orderSaved, "Inserimento avvenuto correttamente");

		} else {

			// crea l'oggetto errore da mandare - settalo nella BaseResponse
			return new BaseResponse(400, null, "Errore nell'inserimento dell'Ordine");
		}
	}

	@Override
	public BaseResponse getAll() {
		
		List<Order> orders = this.orderRepo.findAll();

		return new BaseResponse(200, orders, "Ordini trovati");
	}

	@Override
	public BaseResponse getByOrderNumber(int orderNumber) {

		List<Order> orders = this.orderRepo.getByOrderNumber(orderNumber);

		return new BaseResponse(200, orders, "Ordini trovati con 'numeroOrdine' specificato.");
	}

	@Override
	public BaseResponse getOrderById(String idOrdine) {

		Optional<Order> order = this.orderRepo.findById(idOrdine);

		boolean trovato = order.isPresent();

		if (!trovato) {

			return new BaseResponse(404, null, "Non ho trovato l'ordine con quell'ID");

		} else {

			return new BaseResponse(200, order.get(), "Ordine trovato e restituito (BaseResponse)");
		}
	}

	@Override
	public BaseResponse removeById(String idOrder) {

		Order orderToDelete = this.orderRepo.findById(idOrder).get();

		if (orderToDelete == null) {

			return new BaseResponse(404, null, "Non ho trovato l'ordine da eliminare con quell'ID");

		} else {

			this.orderRepo.deleteById(idOrder);

			return new BaseResponse(200, orderToDelete, "Ordine eliminato correttamente");

		}
	}

	@Override
	public BaseResponse updateOrder(Order orderToUpdate) {

		// L'ordine che arriva ha i campi modificati, ma l'id di quello prima, quindi
		// con l'insert sovrascivo direttamente quello di prima (penso sia un
		// comportamento di mongo)
		BaseResponse response = this.insert(orderToUpdate);

		if (response.getData() == null) {
			response.setMessage("Errore nell'aggiornamento dell'Ordine");
		} else {
			response.setMessage("Aggiornamento avvenuto correttamente");
		}

		return response;
	}

	@Override
	public BaseResponse getOrderWithoutDelivery() {

		return new BaseResponse (200, this.orderRepo.getOrderWithoutDelivery(), "Ordini senza consegna");
	}

}

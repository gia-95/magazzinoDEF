package it.TNetwork.magazzino.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.Delivery;
import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;
import it.TNetwork.magazzino.repository.DeliveryRepository;

@Service("mainDeliveryService")
public class DBDeliveryService implements IDeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;
	

	@Override
	public BaseResponse insert(Delivery delivery) {

		BaseResponse response = null;

		Delivery deliverySaved = this.deliveryRepo.save(delivery);

		if (deliverySaved != null) {
			response = new BaseResponse(200, deliverySaved, "Inserimento avvenuto correttamente");
		} else {
			// crea l'oggetto errore da mandare - settalo nella BaseResponse
		}

		return response;
	}
	

	@Override
	public BaseResponse getAll() {

		return new BaseResponse(200, this.deliveryRepo.findAll(), "Deliveries trovate");
	}
	

	@Override
	public BaseResponse getByDeliveryNumber(String deliveryNumber) {

		BaseResponse response = null;

		Delivery deliveryResponse = this.deliveryRepo.getByDeliveryNumber(deliveryNumber);

		if (deliveryResponse != null) {
			response = new BaseResponse(200, deliveryResponse, "Delivery trovata correttamente, restituita");
		} else {
			// crea l'errore e gfestiscilo - riportalo nella risposta
		}

		return response;
	}

	
	@Override
	public BaseResponse getOrders(String idDelivery) {

		BaseResponse response = null;

		List<Order> orders = new ArrayList<>();

		Optional<Delivery> delivery = this.deliveryRepo.findById(idDelivery);

		if (delivery.isPresent()) {
			for (Order order : delivery.get().getOrdiniAssociati()) {
				orders.add(order);
			}
			response = new BaseResponse(200, orders, "Ordini associati alla Delivery restituiti");
		} else {
			response = new BaseResponse(200, orders, "Delivery con quell'ID non trovata");
		}

		return response;
	}

	
	@Override
	public BaseResponse remove(String idDelivery) {

		BaseResponse response;

		Optional<Delivery> deliveryToDelete = deliveryRepo.findById(idDelivery);

		if (deliveryToDelete.isPresent()) {
			deliveryRepo.deleteById(idDelivery);
			response = new BaseResponse(200, deliveryToDelete.get(), "Eliminazione avvenuta correttamente");
		} else {
			response = new BaseResponse(404, null, "Non eiste una Delivery da eliminare con questo Id.");
		}

		return response;
	}


	@Override
	public BaseResponse getByDeliveryID(String deliveryID) {

		BaseResponse response = null;

		Delivery deliveryResponse = this.deliveryRepo.findById(deliveryID).get();

		if (deliveryResponse != null) {
			response = new BaseResponse(200, deliveryResponse, "Delivery trovata correttamente, restituita");
		} else {
			// crea l'errore e gfestiscilo - riportalo nella risposta
		}

		return response;
	}


	@Override
	public BaseResponse removeOrder(String idDelivery, Order order) {
		
		Delivery delivery = this.deliveryRepo.findById(idDelivery).get();
		
		System.out.println(delivery.getOrdiniAssociati());
		
		delivery.getOrdiniAssociati().remove( delivery.getOrderById(order) );
		
		System.out.println(delivery.getOrdiniAssociati());
		
		Delivery deliverySaved = this.deliveryRepo.save(delivery);

		if (deliverySaved != null) {
			
			return new BaseResponse(200, deliverySaved, "Modifica della Delivery (eliminazione dell'ordine) avvenuta correttamente");
		} else {

			return new BaseResponse(200, null, "Modifica della Delivery (eliminazione dell'ordine) NON avvenuta");

		}

	}

}

package it.TNetwork.magazzino.service;

import java.util.List;

import it.TNetwork.magazzino.model.Order;
import it.TNetwork.magazzino.model.response.BaseResponse;

public interface IOrderService {

	public BaseResponse insert(Order order);

	public BaseResponse getAll();

	public BaseResponse getByOrderNumber(int orderNumber);

	public BaseResponse getOrderById(String idOrdine);

	public BaseResponse removeById(String idOrder);

	public BaseResponse updateOrder(Order orderToUpdate);

	public BaseResponse getOrderWithoutDelivery();

}

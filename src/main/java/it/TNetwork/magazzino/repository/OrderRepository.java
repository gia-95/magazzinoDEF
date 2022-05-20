package it.TNetwork.magazzino.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.TNetwork.magazzino.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	@Query(value = "{ 'numeroOrdine': ?0}")
	List<Order> getByOrderNumber(int orderNumber);

}

package it.TNetwork.magazzino.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.TNetwork.magazzino.model.Delivery;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String>{
	
	@Query(value = "{ 'nDelivery': ?0}")
	Delivery getByDeliveryNumber(String deliveryNumber);

	
}

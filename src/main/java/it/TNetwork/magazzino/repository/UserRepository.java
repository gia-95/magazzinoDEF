package it.TNetwork.magazzino.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.TNetwork.magazzino.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query(value = "{ 'username': ?0}")
	User findByUsername (String username);
	
}

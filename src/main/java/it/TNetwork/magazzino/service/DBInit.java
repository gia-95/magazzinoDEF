package it.TNetwork.magazzino.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import it.TNetwork.magazzino.service.utils.Utility;

import it.TNetwork.magazzino.model.User;
import it.TNetwork.magazzino.repository.UserRepository;

@Service
public class DBInit implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public DBInit(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) {

		// Delete all
		this.userRepository.deleteAll();

		// Create users
		User admin = new User("admin", Utility.encodeBASE64("pass"), "ADMIN", "");
		User user = new User("user", Utility.encodeBASE64("pass"), "USER", "");

		List<User> users = Arrays.asList(admin, user);

		// Save to db
		this.userRepository.saveAll(users);
	}

}

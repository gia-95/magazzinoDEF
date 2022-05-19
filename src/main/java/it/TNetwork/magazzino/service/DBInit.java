package it.TNetwork.magazzino.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.TNetwork.magazzino.model.User;
import it.TNetwork.magazzino.repository.UserRepository;

@Service
public class DBInit {//implements CommandLineRunner {

	/*
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {

		// Delete all
		this.userRepository.deleteAll();

		// Create users
		User admin = new User("admin", this.passwordEncoder.encode("pass"), "ADMIN", "");
		User user = new User("user", this.passwordEncoder.encode("pass"), "USER", "");

		List<User> users = Arrays.asList(admin, user);

		// Save to db
		this.userRepository.saveAll(users);
	}
	*/

}

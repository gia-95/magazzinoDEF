package it.TNetwork.magazzino.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.TNetwork.magazzino.model.User;
import it.TNetwork.magazzino.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {
	
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public List<User> users () {
		return this.userRepository.findAll();
	}

}

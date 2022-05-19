package it.TNetwork.magazzino.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.TNetwork.magazzino.model.request.LoginRequest;
import it.TNetwork.magazzino.model.response.LoginResponse;
import it.TNetwork.magazzino.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	

	@PostMapping(value = "/", produces = "application/json")
	public LoginResponse auth(@RequestBody LoginRequest login) throws Exception {
		LoginResponse loginResponse = this.authService.auth(login);
		return loginResponse;
	}
	
	
}
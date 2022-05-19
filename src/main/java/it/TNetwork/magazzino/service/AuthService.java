package it.TNetwork.magazzino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.TNetwork.magazzino.model.User;
import it.TNetwork.magazzino.model.request.LoginRequest;
import it.TNetwork.magazzino.model.response.LoginResponse;
import it.TNetwork.magazzino.repository.UserRepository;
import it.TNetwork.magazzino.service.utils.TokenUtil;
import it.TNetwork.magazzino.service.utils.Utility;


@Service
@Transactional(readOnly = true)
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtil tokenUtil;


//	@Autowired
//	private MailUtil mailUtil;
//	

	/**
	 * autentica utente e stacca token jwt
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public LoginResponse auth(LoginRequest login) throws Exception {
		LoginResponse response = null;

		if(login != null && login.isValid()) {
			// encode password BASE64 (la password è passata in chiaro ma sul db è criptata)
			
			//String passwordEncoded = Utility.encodeBASE64(login.getPassword());

			// recupero utente (con profilo) tramite username e password
			User utente = this.userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
			// se recupero utente creo un token di autenticazione, altrimenti lancio exception not authorized 
			if(utente != null) {
				// creo token
				String token = this.tokenUtil.createJWT(login.getUsername());

				response = new LoginResponse(200, utente, "login successo", token);
				//response.setUtente(utente);
				response.setToken(token);
			} else {
				response = new LoginResponse(404, utente, "non ho trovaro l'utente", null);

			}
		} 
		return response;
	}
	

}
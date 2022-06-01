package it.TNetwork.magazzino.service;

import org.bson.BasicBSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.TNetwork.magazzino.model.User;
import it.TNetwork.magazzino.model.request.LoginRequest;
import it.TNetwork.magazzino.model.response.LoginResponse;
import it.TNetwork.magazzino.model.response.LoginResponse;
import it.TNetwork.magazzino.model.response.LoginResponseSucces;
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
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public LoginResponse auth(LoginRequest login) throws Exception {
		LoginResponse response = null;

		if (login != null && login.isValid()) {
			// encode password BASE64 (la password è passata in chiaro ma sul db è criptata)
			String passwordEncoded = Utility.encodeBASE64(login.getPassword());

			// recupero utente (con profilo) tramite username e password
			User utente = this.userRepository.findByUsernameAndPassword(login.getUsername(), passwordEncoded);
			// se recupero utente creo un token di autenticazione, altrimenti lancio
			// exception not authorized
			if (utente != null) {
				
				char[] arr = utente.getUsername().toCharArray();
				arr[0] = Character.toUpperCase(arr[0]);
				utente.setUsername(new String(arr));
				
				// creo token
				String token = this.tokenUtil.createJWT(login.getUsername());

				BasicBSONObject obj=new BasicBSONObject();    
				obj.put("token",token);    
				obj.put("utente",utente);   

				response = new LoginResponseSucces("", 200, obj);
				
			} else {
				response = new LoginResponse("Username o password errati", 401);
			}
		}
		return response;
	}

}
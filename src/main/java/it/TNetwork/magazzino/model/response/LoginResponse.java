package it.TNetwork.magazzino.model.response;

public class LoginResponse extends BaseResponse {

	private String token;
	
	public LoginResponse () {}
	
	public LoginResponse(int code, Object object, String message, String token) {
		super(code, object, message);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

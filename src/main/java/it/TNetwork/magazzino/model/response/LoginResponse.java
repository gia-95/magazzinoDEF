package it.TNetwork.magazzino.model.response;

public class LoginResponse {
	
	private int code;
	
	private String message;
	
	public LoginResponse(String message, int code) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

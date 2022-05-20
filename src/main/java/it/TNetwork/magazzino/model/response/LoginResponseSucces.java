package it.TNetwork.magazzino.model.response;

public class LoginResponseSucces extends LoginResponse {
	
	private Object data;
	
	public LoginResponseSucces(String message, int code, Object data) {
		super(message, code);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

}

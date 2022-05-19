package it.TNetwork.magazzino.model.response;

public class BaseResponse {

	private int    code;
	private String message;
	private Object object;
	

	public BaseResponse( int code, Object object, String message) {
		this.code = code;
		this.object = object;
		this.message = message;
	}
	
	public BaseResponse() {}
	
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


	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}
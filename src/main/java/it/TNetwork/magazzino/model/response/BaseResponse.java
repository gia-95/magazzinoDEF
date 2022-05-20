package it.TNetwork.magazzino.model.response;

public class BaseResponse {

	private int    code;
	private String message;
	private Object data;
	

	public BaseResponse( int code, Object data, String message) {
		this.code = code;
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

	
	
}
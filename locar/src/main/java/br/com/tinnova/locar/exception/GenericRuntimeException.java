package br.com.tinnova.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class GenericRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ExceptionResponse response;
	
	public GenericRuntimeException() {
		
	}
	
	public GenericRuntimeException(String message) {
		super(message);
	}
	
	public GenericRuntimeException(Exception e) {
		super(e);
	}
	
	public GenericRuntimeException(String s, Exception e) {
		super(s, e);
	}

	public ExceptionResponse getResponse() {
		return response;
	}

	public void setResponse(ExceptionResponse response) {
		this.response = response;
	}
}

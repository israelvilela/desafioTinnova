package br.com.tinnova.locar.exception;

public class ErrorDetail {
	
	private String message;
    private String field;
    
    public ErrorDetail() {
    	super();
    }
    
	public ErrorDetail(String message, String field) {
		super();
		this.message = message;
		this.field = field;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
   
}
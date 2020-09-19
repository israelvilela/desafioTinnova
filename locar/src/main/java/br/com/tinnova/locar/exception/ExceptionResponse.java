package br.com.tinnova.locar.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {
    
	private int status;
    private List<ErrorDetail> detail;
    private long timestamp;
    private List<String> messages;
    private String exceptionClass;

    public ExceptionResponse() {
    	super();
    }
    
    public ExceptionResponse(int status, List<ErrorDetail> detail, long timestamp,
			List<String> messages, String exceptionClass) {
    	super();
		this.status = status;
		this.detail = detail;
		this.timestamp = timestamp;
		this.messages = messages;
		this.exceptionClass = exceptionClass;
	}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ErrorDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ErrorDetail> detail) {
        this.detail = detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public static final class Builder {
        private int status;
        private List<ErrorDetail> detail;
        private long timestamp;
        private List<String> messages;
        private String exceptionClass;

        private Builder() {
        	detail = new ArrayList<>();
        	messages = new ArrayList<>();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }
        
        public Builder exception(Exception e) {
            this.exceptionClass = e.getClass().getName();
            return this;
        }

        public Builder detail(List<ErrorDetail> detail) {
            this.detail = detail;
            return this;
        }

        public Builder addDetail(ErrorDetail detail) {
            this.detail.add(detail);
            return this;
        }
        
        public Builder messages(List<String> messages) {
        	this.messages = messages;
        	return this;
        }
        
        public Builder addMessage(String message) {
            this.messages.add(message);
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionResponse build() {
            return new ExceptionResponse(status, detail, timestamp, messages, exceptionClass);
        }
    }

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

}

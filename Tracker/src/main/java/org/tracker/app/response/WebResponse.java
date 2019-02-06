package org.tracker.app.response;

public class WebResponse {
	private boolean success = false;
	private Object data = null;
	private String message = null;

	public WebResponse(Object data, boolean success, String message) {
		this.data = data;
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

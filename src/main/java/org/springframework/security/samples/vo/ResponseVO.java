package org.springframework.security.samples.vo;
/**
 * Virtual object to carry the UIMessage 
 * 
 */
public class ResponseVO {
	
	private String successMessage;
	
	private String errorMessage;
	
	private boolean isSuccess;
	
	private Object returnObject;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		// TODO Auto-generated method stub
		this.isSuccess = isSuccess;
	}
}

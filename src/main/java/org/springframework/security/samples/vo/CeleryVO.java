package org.springframework.security.samples.vo;

public class CeleryVO {
 private String status;
 private Object retval;
 private String reason;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Object getRetval() {
	return retval;
}
public void setRetval(Object retval) {
	this.retval = retval;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
}

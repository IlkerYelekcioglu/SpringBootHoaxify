package com.hoaxify.ws.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.Map;

@JsonInclude(value = Include.NON_NULL)
public class ApiError {

  private long timestamp = new Date().getTime();
  private String path;
  private String message;
  private  int status;

  private Map<String,String> validationErrors = null;

  public Map<String, String> getValidationErrors() {
    return validationErrors;
  }

  public void setValidationErrors(Map<String, String> validationErrors) {
    this.validationErrors = validationErrors;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}

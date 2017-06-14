package cn.wcode.dto;

import org.springframework.http.HttpStatus;

/**
 * Created by homiss on 2017/6/8.
 */
public class Result<T> {

  private Boolean success;

  private Integer returnCode;

  private T data;

  private String error;

  public Result(T data) {
    this.success = true;
    this.data = data;
  }

  public Result(String error, Boolean success) {
    this.success = success;
    this.error = error;
  }

  public Result(Boolean success, HttpStatus returnCode, String error) {
    this.success = success;
    this.returnCode = returnCode.value();
    this.error = error;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Integer getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}

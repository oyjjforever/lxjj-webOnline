package com.lxjj.wo.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description= "响应结果对象")
public class ResponseResult<T> {
    @ApiModelProperty(value = "是否成功，true：成功，false：失败",position = 1)
    private boolean success; // 是否成功
    @ApiModelProperty(value = "返回码",position = 2, example = "200")
    private Integer code; // 返回码
    @ApiModelProperty(value = "数据结果",position = 3)
    private T data; // 数据结果
    @ApiModelProperty(value = "提示信息",position = 4)
    private String message; // 提示信息

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseResult(boolean success, StatusCode code) {
        this.success = success;
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ResponseResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(boolean success, String message, StatusCode code) {
        this.success = success;
        this.code = code.getCode();
        this.message =message;
    }

    public ResponseResult(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult success() {
        ResponseResult resp = new ResponseResult(
                true,
                StatusCode.Success);
        return resp;
    }

    public static <T> ResponseResult success(T data) {
        ResponseResult resp = new ResponseResult(
                true,
                StatusCode.Success.getCode(),
                StatusCode.Success.getMessage(),
                data);
        return resp;
    }

    public static ResponseResult success(String message) {
        ResponseResult resp = new ResponseResult(
                true,
                message);
        return resp;
    }

    public static <T> ResponseResult success(String message, T data) {
        ResponseResult resp = new ResponseResult(
                true,
                message, data);
        return resp;
    }

    public static ResponseResult success(StatusCode statusCode,String message) {
        ResponseResult resp = new ResponseResult(
                true,
                message,statusCode);
        return resp;
    }

    public static ResponseResult failure(String message) {
        ResponseResult resp = new ResponseResult(
                false,
                message);
        return resp;
    }

    public static ResponseResult failure(StatusCode errorCode) {
        ResponseResult resp = new ResponseResult(
                false,
                errorCode);
        return resp;
    }

    public static <T> ResponseResult failure(StatusCode errorCode, T data) {
        ResponseResult resp = new ResponseResult(
                false,
                errorCode);
        resp.setData(data);
        return resp;
    }

}

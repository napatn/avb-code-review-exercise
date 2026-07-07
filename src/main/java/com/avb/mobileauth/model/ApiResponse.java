package com.avb.mobileauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

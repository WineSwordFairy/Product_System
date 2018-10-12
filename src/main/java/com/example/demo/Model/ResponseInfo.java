package com.example.demo.Model;

import java.io.Serializable;

public class ResponseInfo implements Serializable {

    private int code;

    private String message;

    private Object Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public ResponseInfo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        Data = data;

    }

    public ResponseInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

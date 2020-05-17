package com.android.project.mvp.model.bean;

import java.io.Serializable;

/**
 * @author Finn
 * @date 2020
 */
public class BaseResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;


    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

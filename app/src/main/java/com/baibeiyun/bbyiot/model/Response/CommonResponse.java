package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;
    public T data;
    public int code;
    public int status_code;
    public String message;


}

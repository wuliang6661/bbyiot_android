package com.baibeiyun.bbyiot.api.httpexception;

/**
 * 解析错误时候
 */

public class ResultException extends RuntimeException {
    private int errCode;
    private String msg;

    public ResultException(int errCode, String msg) {
        super();
        this.errCode = errCode;
        this.msg = msg;
    }


    public int getErrCode() {
        return errCode;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

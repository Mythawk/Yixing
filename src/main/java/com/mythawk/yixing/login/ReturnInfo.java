package com.mythawk.yixing.login;


public class ReturnInfo {
    private int errCode;
    private String msg;
    private Object data;

    public ReturnInfo() {
    }

    public ReturnInfo(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    public ReturnInfo(int errCode, String msg, Object data) {
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

package com.goyo.towermodule.net;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public class BusinessThrowable extends Throwable {

    private int code;
    private String msg;

    public BusinessThrowable(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
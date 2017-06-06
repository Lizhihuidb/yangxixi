package com.lyp.jsonbean;

import java.io.Serializable;

/**
 * 作者:ChenXi 时间:2017/6/6 18: 11
 */
//自主分配
public class IndependentBean implements Serializable{

    /**
     * result : 1
     * message : 分配成功!
     */

    private int result;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.lyc.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义返回值类型
 * @param <T>
 */
@Data
public class ResponseMsg<T> implements Serializable{

    private static final long serialVersionUID = 7302468380401978723L;

    public static final Integer STATUS_SUCCES = 0;

    public static final Integer STATUS_FAIL = -1;

    private Integer status;

    private String  message;

    private T data;

    private Integer total;

    public void success(String msg){
        this.message = msg;
        this.status = STATUS_SUCCES;
    }

    public void fail(String msg){
        this.message = msg;
        this.status = STATUS_FAIL;
    }


}

package com.elaine.core.api;

/**
 * 定义接口返回类型(接收obj类型)
 */
public class ApiResponse<T> {
    //TODO 等待确定接口返回样式
    private int code; //返回的状态码
    private String mssage; //返回的信息，比如成功，验证码已发送等
    private T data;           // 单个对象


    // 判断网络请求是否成功
    public boolean isSuccess() {
        return  code == 200;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return mssage;
    }

    public T getData() {
        return data;
    }
}

package com.elaine.core.api;

/**
 * 定义接口返回类型
 */
public class ApiResponse<T> {
    //TODO 等待确定接口返回样式
    private int code; //返回的状态码
    private String msg; //返回的信息，比如成功，验证码已发送等
    private T obj;           // 单个对象
    private T objLists;       // 数组对象

    private int coin;

    // 判断网络请求是否成功
    public boolean isSuccess() {
        return  code == 200;
    }

    //判断返回的是Bean还是List<Bean>
    public boolean isListOfT() { //当objList不为null时为真
        return objLists != null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getObj() {
        return obj;
    }

    public T getObjList() {
        return objLists;
    }

    public int getCoin() {
        return coin;
    }
}

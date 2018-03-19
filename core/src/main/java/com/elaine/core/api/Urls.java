package com.elaine.core.api;

/**
 * 存放网络请求地址
 */
public class Urls {
    //接口地址
    public static final String BASE_URL = "http://120.27.20.124/shuangyi/api/user/v1.1/demo/";
    public static final String END = ".html";
    //登录注册等账号管理部分
    public static final String LOGIN_URL = BASE_URL + "login"+END;
    public static final String REGISTER_URL = BASE_URL + "register";

}
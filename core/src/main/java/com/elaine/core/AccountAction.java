package com.elaine.core;

import com.elaine.core.model.LocalBean;

import java.util.List;

/**
 * 第一部分：账户管理
 *
 *请求的timestamp、sign、source 均在实现类中直接获取即可，传入参数
 */

public interface AccountAction {

    /**
     * 登陆
     * @param method 方法名
     *@param moblie 手机号
     * @param passwd   密码(用md5加密)
     */
    public void login(String method,String moblie, String passwd, ActionCallback<LocalBean> listener);

    /**
     * 注册
     *@param method 方法名
     * @param mobile 手机号
     * @param code  验证码
     * @invite_code 邀请码
     * @param password_one 登陆密码(用md5加密)
     * @param password_two 确认密码（用md5加密）
     *
     */
    public void register(String method,String mobile, String code, String invite_code, String password_one, String password_two, ActionCallback<List<String>> callback);

    /**
     * 忘记密码
     * @param method 方法名
     * @param mobile 手机号码
     * @param pwd_one 新密码
     * @param pwd_two 重复密码
     * @param code 验证码
     * @param idNumLastSix
     */
    public void fogetPassword(String method,String mobile,String pwd_one,String pwd_two,String code,String idNumLastSix,ActionCallback<List<String>> callback);

    /**
     * 获取验证码
     *
     * @param method 方法名
     * @param mobile 手机号码
     */
    public void acquireAuthCode(String method,String mobile,ActionCallback<Void> callback);

}

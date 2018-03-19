package com.elaine.core;

import com.elaine.core.model.LocalBean;

/**
 * 第一部分：账户管理
 *
 *
 */

public interface AccountAction {

    /**
     * 登陆
     *@param moblie 手机号
     * @param passwd   密码(用md5加密)
     */
    public void login(String moblie, String passwd, ActionCallback<LocalBean> listener);

    /**
     * 注册
     *
     * @param mobile 手机号
     * @param code  验证码
     * @invite_code 邀请码
     * @param password_one 登陆密码(用md5加密)
     * @param password_confirm 确认密码（用md5加密）
     * @param timestamp 时间戳
     *
     */
    public void register(String mobile, String code, String invite_code, String password_one, String password_confirm, String timestamp, ActionCallback<Void> callback);
}

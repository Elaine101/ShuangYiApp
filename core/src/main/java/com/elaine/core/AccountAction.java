package com.elaine.core;

import com.elaine.core.model.CarLevelBean;
import com.elaine.core.model.InsuranceCompanyBean;
import com.elaine.core.model.LocalBean;
import com.elaine.core.model.MyInformBean;
import com.elaine.core.model.UserInformBean;

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
     * @param method 方法名
     * @param mobile 手机号码
     */
    public void acquireAuthCode(String method,String mobile,ActionCallback<Void> callback);

    /**
     * 获取个人信息
     * @param token
     * @param callback
     */
    public void getUserInform(String method,String token,ActionCallback<UserInformBean> callback);

    /**
     * 获取可用的本金券
     * @param method
     * @param token
     * @param callback
     */
    public void getPrincipalTotalAvail(String method,String token,ActionCallback<String>callback);

    /**
     * 获取待生效的本金券
     * @param method
     * @param token
     * @param callback
     */
    public void getPrincipalTotalWillAvail(String method,String token,ActionCallback<String>callback);

    /**
     * 获取可用的奖励券
     * @param method
     * @param token
     * @param callback
     */
    public void getRewardTotalAvail(String method,String token,ActionCallback<String>callback);

    /**
     * 获取待生效的奖励券
     * @param method
     * @param token
     * @param callback
     */
    public void geRewardTotalWillAvail(String method,String token,ActionCallback<String>callback);

    /**
     * 获取我的详情页的信息
     * @param method
     * @param token
     * @param callback
     */
    public void getMyInform(String method,String token,ActionCallback<MyInformBean>callback);

    /**
     * 获取所有的保险公司
     * @param method
     * @param callback
     */
    public void getAllInsuranceCompany(String method, ActionCallback<InsuranceCompanyBean> callback);

    /**
     *
     * @param method
     * @param token
     * @param headImg  头像
     * @param realname 真名
     * @param sex 性别
     * @param idnum  身份证号
     * @param birthday 生日
     * @param carType  汽车类型
     * @param car_buyTime 汽车购买时间
     * @param car_number  车牌号
     * @param insurance_company  保险公司
     * @param insurance_time  参保日期
     * @param callback
     */
    public void suppleInformation(String method,String token,String headImg,String realname,String sex,String idnum,String birthday,String carType,String car_buyTime,String car_number,String insurance_company,String insurance_time, ActionCallback<String> callback);

    /**
     * 获得车类型列表
     * @param method
     * @param callback
     */
    public void getCarLevel(String method,ActionCallback<CarLevelBean> callback);


}

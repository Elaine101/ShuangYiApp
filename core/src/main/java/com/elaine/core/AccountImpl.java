package com.elaine.core;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.elaine.core.api.ApiResponse;
import com.elaine.core.api.Constants;
import com.elaine.core.api.Urls;
import com.elaine.core.model.CarLevelBean;
import com.elaine.core.model.InsuranceCompanyBean;
import com.elaine.core.model.LocalBean;
import com.elaine.core.model.MyInformBean;
import com.elaine.core.model.UserInformBean;
import com.elaine.core.net.OkHttpGetRequest;
import com.elaine.core.net.OkHttpRequest;
import com.elaine.core.net.ResultCallback;
import com.elaine.core.utils.SPUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.List;

/**
 * AccountAction的实现
 */

public class AccountImpl implements  AccountAction{
    private Context mContext;
    //2表示来源为android
    private static final  String SOURCE = "2";
    //密钥
    private static final String SECERT = "shuangyi_android";

    public AccountImpl(Context context){
        this.mContext = context;
    }

    @Override
    public void login(final String method, final String moblie, String passwd, ActionCallback<LocalBean> listener) {
        Type mType = new TypeToken<ApiResponse<LocalBean>>() {}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.MOBILE,moblie)
                .addParams("password",MD5(passwd))
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<LocalBean>(listener,mType){
                    @Override
                    public void onSuccess(LocalBean result) {
                        super.onSuccess(result);
                        SPUtils.putString(mContext,Constants.MOBILE,result.getMobile());
                        SPUtils.putString(mContext,Constants.TOKEN,result.getToken());
                    }
                });

    }

    @Override
    public void register(String method, String mobile, String code, String invite_code, String password_one, String password_two, ActionCallback<List<String>> callback) {
        Type mType = new TypeToken<ApiResponse<List<String>>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.MOBILE,mobile)
                .addParams("code",code)
                .addParams("invite_code",invite_code)
                .addParams("pwd_one",MD5(password_one))
                .addParams("pwd_two",MD5(password_two))
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<List<String>>(callback,mType));
    }

    @Override
    public void fogetPassword(String method,String mobile, String pwd_one, String pwd_two, String code, String idNumLastSix, ActionCallback<List<String>> callback) {
        Type mType = new TypeToken<ApiResponse<List<String>>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.MOBILE,mobile)
                .addParams("pwd_one",MD5(pwd_one))
                .addParams("pwd_two",MD5(pwd_two))
                .addParams("code",code)
                .addParams("idNumLastSix",timestamp)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<List<String>>(callback,mType));
    }

    @Override
    public void acquireAuthCode(String method,String mobile,ActionCallback<Void> callback) {
        Type mType = new TypeToken<ApiResponse<Void>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.MOBILE,mobile)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<Void>(callback,mType));
    }

    @Override
    public void getUserInform(String method, String token, ActionCallback<UserInformBean> callback) {
        Type mType = new TypeToken<ApiResponse<UserInformBean>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<UserInformBean>(callback,mType));
    }

    @Override
    public void getPrincipalTotalAvail(String method, String token, ActionCallback<String> callback) {
        Type mType = new TypeToken<ApiResponse<String>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<String>(callback,mType));
    }

    @Override
    public void getPrincipalTotalWillAvail(String method, String token, ActionCallback<String> callback) {
        Type mType = new TypeToken<ApiResponse<String>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<String>(callback,mType));
    }

    @Override
    public void getRewardTotalAvail(String method, String token, ActionCallback<String> callback) {
        Type mType = new TypeToken<ApiResponse<String>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<String>(callback,mType));
    }

    @Override
    public void geRewardTotalWillAvail(String method, String token, ActionCallback<String> callback) {
        Type mType = new TypeToken<ApiResponse<String>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<String>(callback,mType));
    }

    @Override
    public void getMyInform(String method, String token, ActionCallback<MyInformBean> callback) {
        Type mType = new TypeToken<ApiResponse<MyInformBean>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TOKEN,token)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<MyInformBean>(callback,mType));
    }

    @Override
    public void getAllInsuranceCompany(String method, ActionCallback<InsuranceCompanyBean> callback) {
         Type mType = new TypeToken<ApiResponse<InsuranceCompanyBean>>(){}.getType();
         String timestamp = getTime();
         new OkHttpRequest.Builder()
                 .url(Urls.BASE_URL)
                 .addParams(Constants.METHOD,method)
                 .addParams(Constants.TIMESTAMP,timestamp)
                 .addParams(Constants.SOURCE,SOURCE)
                 .addParams(Constants.SIGN,generateSign(method,timestamp))
                 .post(new ResponseCallback<InsuranceCompanyBean>(callback,mType));
    }

    @Override
    public void suppleInformation(String method, String token, String headImg, String realname, String sex, String idnum, String birthday, String carType, String car_buyTime, String car_number, String insurance_company, String insurance_time, ActionCallback<String> callback) {

    }

    @Override
    public void getCarLevel(String method, ActionCallback<CarLevelBean> callback) {
        Type mType = new TypeToken<ApiResponse<CarLevelBean>>(){}.getType();
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,generateSign(method,timestamp))
                .post(new ResponseCallback<CarLevelBean>(callback,mType));
    }

    public static String getTime(){

        long time=System.currentTimeMillis();//获取系统时间的10位的时间戳

        return String.valueOf(time).substring(0,8);

    }


    public static String MD5(String str)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for(int i = 0; i < charArray.length; i++)
        {
            byteArray[i] = (byte)charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for( int i = 0; i < md5Bytes.length; i++)
        {
            int val = ((int)md5Bytes[i])&0xff;
            if(val < 16)
            {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String generateSign(String method,String timestamp){
        return MD5(method+timestamp+SOURCE+SECERT);
    }


}


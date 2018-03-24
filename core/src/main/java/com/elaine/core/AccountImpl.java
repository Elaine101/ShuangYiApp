package com.elaine.core;

import android.content.Context;
import android.util.Log;

import com.elaine.core.api.ApiResponse;
import com.elaine.core.api.Constants;
import com.elaine.core.api.Urls;
import com.elaine.core.model.LocalBean;
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
    public void login(String method,String moblie, String passwd, ActionCallback<LocalBean> listener) {
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

    public String getTime(){

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


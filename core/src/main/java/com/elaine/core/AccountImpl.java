package com.elaine.core;

import android.content.Context;

import com.elaine.core.api.Urls;
import com.elaine.core.model.LocalBean;
import com.elaine.core.net.OkHttpRequest;

import java.security.MessageDigest;

/**
 * AccountAction的实现
 */

public class AccountImpl implements  AccountAction{
    private Context mContext;

    public AccountImpl(Context context){
        this.mContext = context;
    }

    @Override
    public void login(String moblie, String passwd, ActionCallback<LocalBean> listener) {
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.LOGIN_URL)
                .addParams("method","login")
                .addParams("mobile",moblie)
                .addParams("password",passwd)
                .addParams("timestamp",timestamp)
                .addParams("source","android")
                .addParams("sign",MD5("login"+timestamp+"android"+ "shuangyi_android"))
                .post(new ResponseCallback<LocalBean>(listener));

    }

    @Override
    public void register(String mobile, String code, String invite_code, String password_one, String password_confirm,String timestamp,ActionCallback<Void> callback) {
        new OkHttpRequest.Builder()
                .url(Urls.REGISTER_URL)
                .addParams("mobile",mobile)
                .addParams("code",code)
                .addParams("invite_code",invite_code)
                .addParams("pwd_one",password_one)
                .addParams("pwd_two",password_confirm)
                .addParams("timestamp",timestamp)
                .post(new ResponseCallback<Void>(callback));

    }

    public String getTime(){

        long time=System.currentTimeMillis();//获取系统时间的10位的时间戳

        String  str=String.valueOf(time).substring(0,8);

        return str;

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


}


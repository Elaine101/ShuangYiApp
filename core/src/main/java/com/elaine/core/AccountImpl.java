package com.elaine.core;

import android.content.Context;
import android.util.Log;

import com.elaine.core.api.Urls;
import com.elaine.core.model.LocalBean;
import com.elaine.core.net.OkHttpRequest;

import java.security.MessageDigest;

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
    public void login(String moblie, String passwd, ActionCallback<LocalBean> listener) {
        String method = "login";
        String timestamp = getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams("method",method)
                .addParams("mobile",moblie)
                .addParams("password",MD5(passwd))
                .addParams("timestamp",timestamp)
                .addParams("source",SOURCE)
                .addParams("sign",MD5(method+timestamp+SOURCE+SECERT))
                .post(new ResponseCallback<LocalBean>(listener));

    }

    @Override
    public void register(String mobile, String code, String invite_code, String password_one, String password_confirm,String timestamp,ActionCallback<Void> callback) {
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams("mobile",mobile)
                .addParams("code",code)
                .addParams("invite_code",invite_code)
                .addParams("pwd_one",password_one)
                .addParams("pwd_two",password_confirm)
                .addParams("timestamp",timestamp)
                .post(new ResponseCallback<Void>(callback));

    }

    @Override
    public void fogetPassword(String mobile, String pwd_one, String pwd_two, String code, String idNumLastSix, String timestamp, ActionCallback<Void> callback) {

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


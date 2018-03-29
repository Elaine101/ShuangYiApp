package com.elaine.core;

import android.content.Context;

import com.elaine.core.api.ApiResponse;
import com.elaine.core.api.Constants;
import com.elaine.core.api.Urls;
import com.elaine.core.model.AnnouncementBean;
import com.elaine.core.model.BannerBean;
import com.elaine.core.model.HotGoodsBean;
import com.elaine.core.net.OkHttpRequest;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by elaine on 2018/3/29.
 */

public class MainPageImpl implements MainPageAction {
    private Context mContext;
    //2表示来源为android
    private static final  String SOURCE = "2";
    //密钥
    private static final String SECERT = "shuangyi_android";

    public MainPageImpl(Context context){
        this.mContext = context;
    }

    @Override
    public void getAdvertisementList(String method,ActionCallback<BannerBean> callback) {
        Type mType = new TypeToken<ApiResponse<BannerBean>>() {}.getType();
        String timestamp =AccountImpl.getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,AccountImpl.generateSign(method,timestamp))
                .post(new ResponseCallback<BannerBean>(callback,mType));

    }

    @Override
    public void getHotAnnouncement(String method,ActionCallback<AnnouncementBean> callback) {
        Type mType = new TypeToken<ApiResponse<AnnouncementBean>>() {}.getType();
        String timestamp =AccountImpl.getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,AccountImpl.generateSign(method,timestamp))
                .post(new ResponseCallback<AnnouncementBean>(callback,mType));

    }

    @Override
    public void getHotGoods(String method,ActionCallback<HotGoodsBean> callback) {
        Type mType = new TypeToken<ApiResponse<HotGoodsBean>>() {}.getType();
        String timestamp =AccountImpl.getTime();
        new OkHttpRequest.Builder()
                .url(Urls.BASE_URL)
                .addParams(Constants.METHOD,method)
                .addParams(Constants.TIMESTAMP,timestamp)
                .addParams(Constants.SOURCE,SOURCE)
                .addParams(Constants.SIGN,AccountImpl.generateSign(method,timestamp))
                .post(new ResponseCallback<HotGoodsBean>(callback,mType));
    }
}

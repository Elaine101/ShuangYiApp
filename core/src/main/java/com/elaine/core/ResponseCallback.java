package com.elaine.core;

/**
 * 网络请求响应接口
 */


import android.util.Log;

import com.elaine.core.api.ApiResponse;
import com.elaine.core.net.ResultCallback;
import com.squareup.okhttp.Request;

import org.json.JSONTokener;

import java.lang.reflect.Type;

/**
 * 实现网络请求的回调，对返回的信息做统一的处理
 * 子对象可直接调用其onSuccess()和onFailure方法进行处理
 *对于 json 的键是 obj 的使用
 * TODO 貌似还有问题
 */
public class ResponseCallback<T> extends ResultCallback<ApiResponse<T>> {

    private ActionCallback<T> callback;


    public ResponseCallback(ActionCallback<T> listener){
        this.callback = listener;
    }

    public ResponseCallback(ActionCallback<T> callback, Type type) {
        this.mType = type;
        this.callback = callback;
    }

    @Override
    public void onError(Request request, Exception e) {
        Log.d("TAG", "onError: "+request.toString());
        Log.d("TAG", "onError: "+e.toString());
        callback.onError();
    }

    @Override
    public void onResponse(ApiResponse<T> response) {
        if(response!=null){
            if(response.isSuccess()){
                onSuccess(response.getData());
            }
            }else{
                onFailure(response.getCode(),response.getMsg());
            }

    }


    public void onSuccess(T result){
        callback.onSuccess(result);
    }

    public void onFailure(int errCode, String msg){
        Log.d("TAG", "onError: "+errCode);
        Log.d("TAG", "onError: "+msg);
        callback.onFailure(errCode,msg);
    }

    @Override
    public void onBefore(Request request) {
        callback.onBefore();
    }

    @Override
    public void onAfter() {
        callback.onAfter();
    }

    @Override
    public void inProgress(float progress) {
        callback.inProgress(progress);
    }

}
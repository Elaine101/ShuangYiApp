package com.elaine.shuangyiapp;

import android.content.Context;
import android.widget.Toast;

import com.elaine.core.ActionCallback;


/**
 * Created by lcodecore on 16/4/18.
 * ActionCallback接口的一个简单实现
 */
public class ActionCBImpl<T> implements ActionCallback<T> {
    private Context mContext;

    public ActionCBImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSuccess(T data) {

    }

    public void onFailure(int errorCode, String messge) {
        //TODO 对规定的错误的状态码进行统一的处理
        //先简单的对返回做Toast处理
        if (mContext != null)
            Toast.makeText(mContext, messge, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBefore() {

    }

    @Override
    public void onAfter() {

    }

    @Override
    public void inProgress(float progress) {

    }

    @Override
    public void onError() {

    }
}

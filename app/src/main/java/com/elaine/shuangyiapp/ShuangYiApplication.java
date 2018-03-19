package com.elaine.shuangyiapp;

import android.app.Application;

import com.elaine.core.AccountAction;
import com.elaine.core.AccountImpl;

/**
 * Created by elaine on 2018/3/19.
 */

public class ShuangYiApplication extends Application {
    private AccountAction accountAction;

    private static ShuangYiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance==null){
            instance = this;
        }

        //核心层网络请求实例化
        accountAction  = new AccountImpl(this);

    }

    public AccountAction getAccountAction(){
        return accountAction;
    }

    public static ShuangYiApplication getInstance(){
        return instance;
    }

}

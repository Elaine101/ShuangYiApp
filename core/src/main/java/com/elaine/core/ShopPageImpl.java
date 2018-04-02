package com.elaine.core;

import android.content.Context;

/**
 * Created by elaine on 2018/3/31.
 */

public class ShopPageImpl implements ShopPageAction {
    public Context mContext;
    //2表示来源为android
    private static final  String SOURCE = "2";
    //密钥
    private static final String SECERT = "shuangyi_android";

    public ShopPageImpl(Context mContext){this.mContext = mContext;}
}

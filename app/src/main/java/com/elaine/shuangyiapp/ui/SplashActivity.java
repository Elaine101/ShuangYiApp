package com.elaine.shuangyiapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;


import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ui.account.LoginFragment;
import com.elaine.shuangyiapp.ui.account.RegisterFragment;
import com.elaine.shuangyiapp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/19.
 */

public class SplashActivity extends BaseActivity {
    private Handler handler=new Handler();


    @Override
    public int setInflateId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },1500);
    }
}


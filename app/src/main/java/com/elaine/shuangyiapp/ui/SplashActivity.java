package com.elaine.shuangyiapp.ui;

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

    @BindView(R.id.bt_login) Button bt_login;
    @BindView(R.id.bt_register) Button bt_register;

    @OnClick(R.id.bt_login)void login(){
        getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).addToBackStack("").commit();
    }

    @OnClick(R.id.bt_register) void register(){
        getSupportFragmentManager().beginTransaction().add(R.id.container, new RegisterFragment()).addToBackStack("").commit();
    }

    @Override
    public int setInflateId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {

    }
}


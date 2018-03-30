package com.elaine.shuangyiapp.ui.account;

import android.widget.Button;

import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/26.
 */

public class LoginAndRegisterFragment extends BaseFragment {
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_register) Button bt_register;

    @OnClick(R.id.bt_login)void login(){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).addToBackStack("").commit();
    }

    @OnClick(R.id.bt_register) void register(){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new RegisterFragment()).addToBackStack("").commit();
    }
    @Override
    public int setInflateId() {
        return R.layout.fragment_login_and_register;
    }

    @Override
    public void init() {

    }
}

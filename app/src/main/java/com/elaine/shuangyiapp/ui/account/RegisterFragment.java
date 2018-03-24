package com.elaine.shuangyiapp.ui.account;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.ui.base.BaseFragment;
import com.elaine.shuangyiapp.views.TimeCounter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/21.
 */

public class RegisterFragment extends BaseFragment {
    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_auth_code)
    EditText et_authCode;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirm_password)
    EditText et_confirmPassword;
    @BindView(R.id.et_invite_code)
    EditText et_invitCode;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;
    @BindView(R.id.tv_user_protocol)
    TextView tv_userProtocol;
    @BindView(R.id.bt_submit)
    Button submit;
    @BindView(R.id.bt_acquire_auth_code)
    Button bt_acuqireAuthCode;
    @BindView(R.id.bt_back)
    Button bt_back;

    TimeCounter timeCounter;

    @OnClick(R.id.bt_submit)void submit(){
        String mobile = et_phoneNum.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String password_confirm = et_confirmPassword.getText().toString().trim();
        String authCode = et_authCode.getText().toString().trim();
        String invitedCode = et_invitCode.getText().toString().trim();
        Boolean protocolAgree = cb_agree.isChecked();

        if (TextUtils.isEmpty(mobile)){
            showToast("请输入手机号码");
        }
        if (TextUtils.isEmpty(password)){
            showToast("请输入登陆密码");
        }
        if (TextUtils.isEmpty(password_confirm)){
            showToast("请再次输入密码");
        }
        if (TextUtils.isEmpty(authCode)){
            showToast("请输入验证码");
        }
        if (!protocolAgree){
            showToast("请阅读用户协议并同意用户协议");
        }
        ShuangYiApplication.getInstance().getAccountAction().register("register",mobile,authCode,invitedCode,password,password_confirm,new ActionCBImpl<List<String>>(getContext()){
            @Override
            public void onSuccess(List<String> data) {
                super.onSuccess(data);
                Log.d("TAG:", "onSuccess: "+"注册成功");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    @OnClick(R.id.bt_acquire_auth_code) void acquireCode(){
        String mobile = et_phoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)){
            showToast("请输入手机号码");
        } else if(mobile.length()!=11){
            showToast("请输入正确的手机号码");
        }

        ShuangYiApplication.getInstance().getAccountAction().acquireAuthCode("get_code",mobile,new ActionCBImpl<Void>(getContext()){
            @Override
            public void onSuccess(Void data) {
                super.onSuccess(data);
                timeCounter.start();
                showToast("验证码已发送");
            }
        });
    }

    @OnClick(R.id.tv_user_protocol)void enterProtocolFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new UserProtocolFragment()).addToBackStack("").commit();
    }

    @OnClick(R.id.bt_back) void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public int setInflateId() {
        return R.layout.fragment_register;
    }

    @Override
    public void init() {
        //计时1分钟，计时间隔1s
        timeCounter = new TimeCounter(60000, 1000, bt_acuqireAuthCode);
    }
}

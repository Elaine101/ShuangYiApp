package com.elaine.shuangyiapp.ui.account;

import android.media.Image;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.ui.base.BaseFragment;
import com.elaine.shuangyiapp.views.TimeCounter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/22.
 */

public class ForgetPasswordFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_auth_code)
    EditText et_authCode;
    @BindView(R.id.bt_acquire_auth_code)
    Button bt_acquire_auth_code;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirm_password)
    EditText et_confirmPassword;
    @BindView(R.id.et_id_num_last_six)
    EditText et_idNumLastSix;
    TimeCounter timeCounter;
    @BindView(R.id.bt_finish)
    Button bt_finish;

    @OnClick(R.id.iv_back) void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.bt_acquire_auth_code) void acquireAuthCode(){
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

    @OnClick(R.id.bt_finish) void submit(){
        String mobile = et_phoneNum.getText().toString().trim();
        String authCode = et_authCode.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String passwordConfirm = et_confirmPassword.getText().toString().trim();
        String idNumLastSix = et_idNumLastSix.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)){
            showToast("请输入手机号码");
        }
        if (TextUtils.isEmpty(authCode)){
            showToast("请输入验证码");
        }
        if (TextUtils.isEmpty(password)){
            showToast("请输入密码");
        }
        if (TextUtils.isEmpty(passwordConfirm)){
            showToast("请再次输入密码");
        }
        if (TextUtils.isEmpty(idNumLastSix)){
            showToast("请输入身份证后六位");
        }
        ShuangYiApplication.getInstance().getAccountAction().fogetPassword("reset_password",mobile,password,passwordConfirm,authCode,idNumLastSix,new ActionCBImpl<List<String>>(getContext()){
            @Override
            public void onSuccess(List<String> data) {
                super.onSuccess(data);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public int setInflateId() {
        return R.layout.fragment_forget_password;
    }

    @Override
    public void init() {
        //计时1分钟，计时间隔1s
        timeCounter = new TimeCounter(60000, 1000, bt_acquire_auth_code);
    }
}

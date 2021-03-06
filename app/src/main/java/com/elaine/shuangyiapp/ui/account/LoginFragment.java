package com.elaine.shuangyiapp.ui.account;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elaine.core.model.LocalBean;
import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.ui.MainActivity;
import com.elaine.shuangyiapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.elaine.core.AccountImpl.MD5;

/**
 * Created by elaine on 2018/3/19.
 */

public class LoginFragment extends BaseFragment {
   @BindView(R.id.bt_back) Button bt_back;
   @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
   @BindView(R.id.et_password)
   EditText et_password;
   @BindView(R.id.bt_login)
   Button bt_login;
   @BindView(R.id.tv_fastRegister)
    TextView tv_fastRegister;
   @BindView(R.id.tv_fogetPassword)
   TextView tv_fogetPassword;

   @OnClick(R.id.bt_login)void login(){
       String phoneNum = et_phoneNum.getText().toString().trim();
       String passWord = et_password.getText().toString().trim();
       if (TextUtils.isEmpty(phoneNum)){
           showToast("请输入手机号码");
       }
       if (TextUtils.isEmpty(passWord)){
           showToast("请输入密码");
       }
       login(phoneNum,passWord);
   }

   @OnClick(R.id.bt_back) void back(){
       getActivity().getSupportFragmentManager().popBackStack();
   }


    @OnClick(R.id.tv_fastRegister) void fastRegister(){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new RegisterFragment()).addToBackStack("").commit();
   }

   @OnClick(R.id.tv_fogetPassword)void fogetPassword(){
       getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new ForgetPasswordFragment()).addToBackStack("").commit();
   }

    @Override
    public int setInflateId() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
    }


    private void login(String phoneNum, String passWord) {
        final ProgressDialog waitingDialog= new ProgressDialog(getActivity());
        waitingDialog.setTitle("正在登陆");
        waitingDialog.setMessage("请稍后...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();

       ShuangYiApplication.getInstance().getAccountAction().login("login",phoneNum,passWord,new ActionCBImpl<LocalBean>(getActivity()){
           @Override
           public void onSuccess(LocalBean data) {
               super.onSuccess(data);
               startActivity(MainActivity.class);
               getActivity().getSupportFragmentManager().popBackStack();
               getActivity().finish();
           }

           @Override
           public void onAfter() {
               super.onAfter();
               waitingDialog.dismiss();
           }
       });
    }


}

package com.elaine.shuangyiapp.ui.account;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elaine.core.utils.SPUtils;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/26.
 */

public class SettingFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rl_my_inform)
    RelativeLayout rl_myInform;
    @BindView(R.id.rl_account_security)
    RelativeLayout rl_accpunt_security;
    @BindView(R.id.rl_message_remind)
    RelativeLayout rl_messageRemind;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout rl_clearCache;
    @BindView(R.id.rl_about_us)
    RelativeLayout rl_aboutUs;
    @BindView(R.id.rl_rate_us)
    RelativeLayout rl_rateUs;
    @BindView(R.id.rl_service_center)
    RelativeLayout rl_serviceCenter;
    @BindView(R.id.bt_log_out)
    Button bt_logOut;

    @OnClick(R.id.iv_back)void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.bt_log_out) void logOut(){
        SPUtils.clear(getContext());
        Intent startActivityIntent = new Intent(getContext(), LoginActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(startActivityIntent);
    }

    @Override
    public int setInflateId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void init() {

    }
}

package com.elaine.shuangyiapp.ui.account;

import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by elaine on 2018/3/22.
 */

public class UserProtocolFragment extends BaseFragment {
    @OnClick(R.id.bt_back) void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public int setInflateId() {
        return R.layout.fragment_user_protocol;
    }

    @Override
    public void init() {

    }
}

package com.elaine.shuangyiapp.ui;



import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.ll_bar_scan)
    LinearLayout ll_toolbarScan;
    @BindView(R.id.ll_bar_message)
    LinearLayout ll_toolbarMessage;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_clear)
    ImageView iv_clearSearch;




    @OnClick(R.id.ll_bar_scan)void scan(){
    }

    @OnClick(R.id.ll_bar_scan)void getMessage(){

    }

    @Override
    public int setInflateId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}

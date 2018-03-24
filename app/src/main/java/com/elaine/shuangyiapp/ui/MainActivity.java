package com.elaine.shuangyiapp.ui;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.classify.ClassifyPageFragment;
import com.elaine.shuangyiapp.shop.AlternativePageFragment;
import com.elaine.shuangyiapp.shop.ShopPageFragment;
import com.elaine.shuangyiapp.ui.account.MePageFragment;
import com.elaine.shuangyiapp.ui.base.BaseActivity;
import com.elaine.shuangyiapp.ui.recommend.RecommendPageFragment;

import java.util.ArrayList;

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_tab_RadioGroup)
    RadioGroup radioGroup;
    




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
        radioGroup.setOnCheckedChangeListener(this);

        initData();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_recommend :
                break;
            case R.id.radio_classify :
                break;
            case R.id.radio_shop:
                break;
            case R.id.radio_alternative:
                break;
            case R.id.radio_me:
                break;
        }

    }

    public void initData(){

    }

}

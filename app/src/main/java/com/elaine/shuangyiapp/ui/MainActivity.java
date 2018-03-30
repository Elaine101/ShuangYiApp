package com.elaine.shuangyiapp.ui;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.elaine.core.api.Constants;
import com.elaine.core.utils.SPUtils;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.classify.ClassifyPageFragment;
import com.elaine.shuangyiapp.shop.AlternativePageFragment;
import com.elaine.shuangyiapp.shop.ShopPageFragment;
import com.elaine.shuangyiapp.ui.account.LoginFragment;
import com.elaine.shuangyiapp.ui.account.MePageFragment;
import com.elaine.shuangyiapp.ui.account.SettingFragment;
import com.elaine.shuangyiapp.ui.base.BaseActivity;
import com.elaine.shuangyiapp.ui.recommend.RecommendPageFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.ll_bar_left)
    LinearLayout ll_toolbarLeft;
    @BindView(R.id.ll_bar_right)
    LinearLayout ll_toolbarRight;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_clear)
    ImageView iv_clearSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_tab_RadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.iv_bar_left)
    ImageView iv_barLeft;
    @BindView(R.id.iv_bar_right)
    ImageView iv_barRight;
    @BindView(R.id.tv_bar_message)
    TextView tv_barMessage;
    @BindView(R.id.tv_bar_scan)
    TextView tv_barScan;

    private int currentFragmenId = -1;
    public RecommendPageFragment recommendPageFragment;
    public ClassifyPageFragment classifyPageFragment;
    public AlternativePageFragment alternativePageFragment;
    public ShopPageFragment shopPageFragment;
    public MePageFragment mePageFragment;

    public static final String CURRENTPOSITION = "current_position";



    @OnClick(R.id.ll_bar_left)void clickLeft(){
        if (currentFragmenId ==4){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new SettingFragment(),"").addToBackStack("").commit();
        }
    }

    @OnClick(R.id.ll_bar_right)void getMessage(){

    }

    @Override
    public int setInflateId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initData();
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_recommend :
                setFragment(0);
                break;
            case R.id.radio_classify :
                setFragment(1);
                break;
            case R.id.radio_shop:
                setFragment(2);
                break;
            case R.id.radio_alternative:
                if (TextUtils.isEmpty(SPUtils.getString(this,Constants.TOKEN))){
                  getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).addToBackStack("").commit();
                }else {
                    setFragment(3);
                }
                break;
            case R.id.radio_me:
                if (TextUtils.isEmpty(SPUtils.getString(this,Constants.TOKEN))){
                    getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).addToBackStack("").commit();
                }else {
                setFragment(4);
                }
                break;
                default:
                    break;
        }

    }

    public void initData(){
        setFragment(0);

    }

    public void setFragment(int fragmentId){
        if (fragmentId==4){
            tv_barScan.setVisibility(View.GONE);
            tv_barMessage.setVisibility(View.GONE);
            iv_barLeft.setImageResource(R.drawable.iv_setting);
        }else {
            tv_barMessage.setVisibility(View.VISIBLE);
            tv_barScan.setVisibility(View.VISIBLE);
            iv_barLeft.setImageResource(R.drawable.icon_scan_gray);
        }
        if (currentFragmenId !=fragmentId){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment currentFragment = getFragment(fragmentId);
            if (currentFragment!=null){
                if (!currentFragment.isAdded()){
                    fragmentTransaction.add(R.id.main_pager_container, currentFragment, currentFragment.getTag());
                }
                List<Fragment> fragmentList =getSupportFragmentManager().getFragments();
                for (Fragment mFragment : fragmentList) {
                    //将除了要显示的fragment外的另外四个fragment隐藏起来
                    if (!(mFragment.isHidden())&&!(mFragment.getClass().equals(currentFragment.getClass()))){
                        fragmentTransaction.hide(mFragment);
                    }
                }

                fragmentTransaction.show(currentFragment);
                currentFragmenId = fragmentId;
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

    public Fragment getFragment(int fragmentId){
        switch (fragmentId){
            //recommendPage 首页
            case 0:
                if(recommendPageFragment == null){
                    recommendPageFragment = new RecommendPageFragment();
                }
                return recommendPageFragment;
            //classifyPage 分类页
            case 1:
                if (classifyPageFragment == null){
                    classifyPageFragment = new ClassifyPageFragment();
                }

                return classifyPageFragment;
            //shopPage 商店页
            case 2:
                if (shopPageFragment == null){
                    shopPageFragment = new ShopPageFragment();
                }
                return shopPageFragment;
            //alternativePage 购物车页
            case 3:
                if (alternativePageFragment == null){
                    alternativePageFragment = new AlternativePageFragment();
                }

               return alternativePageFragment;
            //mePage 个人页面
            case 4:
                if (mePageFragment == null){
                    mePageFragment = new MePageFragment();
                }

                return mePageFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //当activity转入后台时手动保存当前fragment下标
        outState.putInt(CURRENTPOSITION, currentFragmenId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentFragmenId = savedInstanceState.getInt(CURRENTPOSITION);
        setFragment(currentFragmenId);
        super.onRestoreInstanceState(savedInstanceState);
    }
}

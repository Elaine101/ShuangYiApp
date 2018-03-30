package com.elaine.shuangyiapp.ui.account;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elaine.core.api.Constants;
import com.elaine.core.model.MyInformBean;
import com.elaine.core.utils.SPUtils;
import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.ui.base.BaseFragment;


import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by elaine on 2018/3/24.
 */

public class MePageFragment extends BaseFragment {
    @BindView(R.id.rl_tab)
    RelativeLayout rl_tab;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.tv_real_name)
    TextView tv_realName;
    @BindView(R.id.tv_member_id)
    TextView tv_memberID;
    @BindView(R.id.ll_focus_goods)
    LinearLayout ll_focusGoods;
    @BindView(R.id.ll_focus_shop)
    LinearLayout ll_focusShop;
    @BindView(R.id.tv_focus_goods_num)
    TextView tv_focusGoodsNum;
    @BindView(R.id.tv_focus_shops_num)
    TextView tv_focusShopsNum;
    @BindView(R.id.rl_my_order)
    RelativeLayout rl_myOrder;
    @BindView(R.id.rl_my_principal_coupon)
    RelativeLayout rl_myPrincipalCoupon;
    @BindView(R.id.tv_prin_available_money)
    TextView tv_prinAvailableMoney;
    @BindView(R.id.tv_prin_tbc_money)
    TextView tv_printTBCMoney;
    @BindView(R.id.rl_my_reward_coupon)
    RelativeLayout rl_myRewardCoupon;
    @BindView(R.id.tv_reward_available_money)
    TextView tv_rewardAvailableMoney;
    @BindView(R.id.tv_reward_tbc_money)
    TextView tv_rewardTBCMoney;
    @BindView(R.id.rl_my_pay)
    RelativeLayout rl_myPay;
    @BindView(R.id.rl_my_insurance)
    RelativeLayout rl_myInsurance;
    @BindView(R.id.rl_my_footPrint)
    RelativeLayout rl_myFootPrint;

    @OnClick(R.id.rl_tab)void personalCenter(){
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new PersonCenterFragment(),"").addToBackStack("").commit();
    }

    @Override
    public int setInflateId() {
        return R.layout.fragment_main_me_page;
    }

    @Override
    public void init() {
        ShuangYiApplication.getInstance().getAccountAction().getMyInform("get_my_info",SPUtils.getString(getContext(),Constants.TOKEN),new ActionCBImpl<MyInformBean>(getContext()){
                   @Override
                   public void onSuccess(MyInformBean data) {
                       tv_realName.setText(data.getName());
                       Log.d("TAG:", "onSuccess: "+data.getPrincipalUseBill());
                       tv_memberID.setText("会员卡号："+data.getCardId());
                       loadAvatar(iv_avatar,data.getImageUrl());
                       tv_focusGoodsNum.setText(data.getFocusProductNum());
                       tv_focusShopsNum.setText(data.getFocusShopNum());
                       tv_prinAvailableMoney.setText("可用金额  "+data.getPrincipalUseBill());
                       tv_printTBCMoney.setText("待生效金额  "+data.getPrincipalUnuseBill());
                       tv_rewardAvailableMoney.setText("可用金额  "+data.getRewardUseBill());
                       tv_rewardTBCMoney.setText("待生效金额  "+data.getRewardUnuseBill());
                   }
               });
            }


    public void loadAvatar(CircleImageView iv, String hash) {
        if (TextUtils.isEmpty(hash)) {
            iv.setImageResource(R.drawable.iv_me_defalut_avatar);
        } else {
            String imgUrl = hash;
            Glide.with(ShuangYiApplication.getInstance()).load(imgUrl)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
        }
    }



}

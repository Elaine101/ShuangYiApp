package com.elaine.shuangyiapp.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elaine.core.model.AnnouncementBean;
import com.elaine.core.model.BannerBean;
import com.elaine.core.model.HotGoodsBean;
import com.elaine.core.model.MainHeadBean;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.adapter.holder.CommonHolder;
import com.elaine.shuangyiapp.utils.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by elaine on 2018/3/28.
 */

public class RecommendPageAdapter extends BaseRecyclerAdapter<HotGoodsBean.GoodsEntity> {
    List<AnnouncementBean.NoticesEntity> announcementList =new ArrayList<>();
    List<BannerBean.AdsEntity> adsList = new ArrayList<>();
    HeadHolder headHolder;
    MainHeadBean headData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        rootView = parent;
        //设置ViewHolder
        int type = getItemViewType(position);
        if (type == TYPE_HEAD) {
            this.headHolder = new HeadHolder(parent.getContext(),parent);
            return headHolder;
        } else {
            return setViewHolder(parent);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(headHolder!=null){
            if (position ==TYPE_HEAD){
                ((HeadHolder)holder).bindData(headData);
            }else {
                super.onBindViewHolder(holder, position);
            }
        }else {
            super.onBindViewHolder(holder, position);
        }

    }

    public void setHeadData(MainHeadBean mainHeadBean){
        headData = mainHeadBean;
        notifyDataSetChanged();
    }

    public void setHeadAds(List<BannerBean.AdsEntity> adsList){
        headData.setAds(adsList);
        notifyDataSetChanged();
    }

    public void setHeadNotice(List<AnnouncementBean.NoticesEntity> noticesList){
        headData.setNotices(noticesList);
        notifyDataSetChanged();
    }


    @Override
    public CommonHolder<HotGoodsBean.GoodsEntity> setViewHolder(ViewGroup parent) {
        return new RecommendHolder(parent.getContext(),parent);
    }

    class RecommendHolder extends CommonHolder<HotGoodsBean.GoodsEntity>{
        @BindView(R.id.iv_pic_introduce)
        ImageView iv_pic;
        @BindView(R.id.tv_introduce)
        TextView tv_introduce;
        @BindView(R.id.tv_location)
        TextView tv_location;

        public RecommendHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_main_recommend);
        }

        @Override
        public void bindData(HotGoodsBean.GoodsEntity goodsEntity) {
            tv_introduce.setText(goodsEntity.getTitle());
            String imgPicUrl = goodsEntity.getImgUrl();
            Glide.with(getContext()).load(imgPicUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.iv_pic_introduce_default)
                    .crossFade()
                    .into(iv_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: ");
                }
            });
        }
    }

    static class HeadHolder extends CommonHolder<MainHeadBean> {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.tv_head_insurance)
        TextView tv_insurance;
        @BindView(R.id.tv_head_order)
        TextView tv_order;
        @BindView(R.id.tv_head_reward)
        TextView tv_reward;
        @BindView(R.id.ll_notice)
        LinearLayout ll_notice;
        @BindView(R.id.vflipper_notice)
        ViewFlipper viewFlipper;

        @Override
        public void bindData(MainHeadBean mainHeadBean) {
            //配置banner
            List<String> imageUrls = new ArrayList<>();
            if (mainHeadBean.getAds()!=null){
                for (int i=0;i<mainHeadBean.getAds().size();i++){
                    imageUrls.add(mainHeadBean.getAds().get(i).getImgUrl());
                }
            }

            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            banner.setImageLoader(new BannerImageLoader());
            //设置banner动画效果
            banner.setBannerAnimation(Transformer.RotateDown);
            //设置图片集合
            banner.setImages(imageUrls);
            //设置轮播时间
            banner.setDelayTime(3500);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();

            //配置循环滚动控件
            if (mainHeadBean.getNotices()!=null){
                for (int i=0;i<mainHeadBean.getNotices().size();i++){
                    TextView tv_notice = new TextView(getContext());
                    tv_notice.setText(mainHeadBean.getNotices().get(i).getTitle());
                    tv_notice.setTextSize(10);
                    tv_notice.setTextColor(ColorStateList.valueOf(Color.WHITE));
                    tv_notice.setMaxLines(2);
                    tv_notice.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    viewFlipper.addView(tv_notice);
                }
            }

            tv_insurance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: insurance");
                }
            });
            tv_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: order");
                }
            });
            tv_reward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: reward");
                }
            });
            ll_notice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: notice");
                }
            });
        }


        public HeadHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_main_recommend_head);
        }

    }
}

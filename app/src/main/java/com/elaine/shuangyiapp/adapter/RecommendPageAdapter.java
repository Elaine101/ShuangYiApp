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


}

package com.elaine.shuangyiapp.ui.recommend;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.elaine.core.model.AnnouncementBean;
import com.elaine.core.model.BannerBean;
import com.elaine.core.model.HotGoodsBean;
import com.elaine.core.model.MainHeadBean;
import com.elaine.shuangyiapp.ActionCBImpl;
import com.elaine.shuangyiapp.R;
import com.elaine.shuangyiapp.ShuangYiApplication;
import com.elaine.shuangyiapp.adapter.RecommendPageAdapter;
import com.elaine.shuangyiapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by elaine on 2018/3/24.
 */

public class RecommendPageFragment extends BaseFragment {
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GridLayoutManager gridLayoutManager;
    RecommendPageAdapter recommendPageAdapter;

    List<BannerBean.AdsEntity> adsList;
    List<HotGoodsBean.GoodsEntity> goodsList;
    List<AnnouncementBean.NoticesEntity> noticesList;
    MainHeadBean headBean= new MainHeadBean();

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //更新banner处的UI
                case 0:
                    recommendPageAdapter.setHeadAds(adsList);
                    break;
                    //更新热门商品处的UI
                case 1:
                    recommendPageAdapter.setDataList(goodsList);
                    break;
                    //更新公告出的UI
                case 2:
                    recommendPageAdapter.setHeadNotice(noticesList);
                    break;
            }
        }
    };

    @Override
    public int setInflateId() {
        return R.layout.fragment_main_recommend_page;
    }

    @Override
    public void init() {
       adsList = new ArrayList<>();
       goodsList = new ArrayList<>();
       noticesList= new ArrayList<>();
       //设置recycleView的参数
       gridLayoutManager = new GridLayoutManager(getContext(),2);
       //设置header的spanSize为2
       gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

           @Override
           public int getSpanSize(int position) {
               if (position == 0){
                   return 2;
               }else {
                   return 1;
               }
           }
       });
       recyclerView.setLayoutManager(gridLayoutManager);
       recommendPageAdapter = new RecommendPageAdapter();
       //设置允许head及head的参数
       recommendPageAdapter.setEnableHead(true);
       recommendPageAdapter.setHeadData(headBean);
       View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_main_recommend_head, null);
       getInternetRequest();
       recyclerView.setAdapter(recommendPageAdapter);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout != null) refreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });

    }

    private void getInternetRequest() {
          BannerThread bannerThread = new BannerThread();
          bannerThread.start();
          HotGoods hotGoods = new HotGoods();
          hotGoods.start();
          AnnouncementThread announcement = new AnnouncementThread();
          announcement.start();

    }

    class BannerThread extends Thread{
        @Override
        public void run() {
            super.run();
            ShuangYiApplication.getInstance().getMainPageAction().getAdvertisementList("get_advertisement_list",new ActionCBImpl<BannerBean>(getContext()){
                @Override
                public void onSuccess(BannerBean data) {
                    super.onSuccess(data);
                    adsList = data.getAds();
                    handler.sendEmptyMessage(0);
                }
            });

        }
    }
    class HotGoods extends Thread{
        @Override
        public void run() {
            super.run();
            ShuangYiApplication.getInstance().getMainPageAction().getHotGoods("get_hot_goods",new ActionCBImpl<HotGoodsBean>(getContext()){
                @Override
                public void onSuccess(HotGoodsBean data) {
                    super.onSuccess(data);
                    goodsList = data.getGoods();
                    handler.sendEmptyMessage(1);
                }
            });

        }
    }
    class AnnouncementThread extends Thread{
        @Override
        public void run() {
            super.run();
            ShuangYiApplication.getInstance().getMainPageAction().getHotAnnouncement("get_hot_announcement",new ActionCBImpl<AnnouncementBean>(getContext()){
                @Override
                public void onSuccess(AnnouncementBean data) {
                    super.onSuccess(data);
                    noticesList = data.getNotices();
                    handler.sendEmptyMessage(2);
                }
            });

        }
    }

}

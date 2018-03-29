package com.elaine.core.model;

import java.util.List;

/**
 * 首页的head使用的数据
 */

public class MainHeadBean {
    private List<HotGoodsBean.GoodsEntity> goods;
    private List<BannerBean.AdsEntity> ads;
    private List<AnnouncementBean.NoticesEntity> notices;

    public void setGoods(List<HotGoodsBean.GoodsEntity> goods) {
        this.goods = goods;
    }

    public void setAds(List<BannerBean.AdsEntity> ads) {
        this.ads = ads;
    }

    public void setNotices(List<AnnouncementBean.NoticesEntity> notices) {
        this.notices = notices;
    }

    public List<HotGoodsBean.GoodsEntity> getGoods() {

        return goods;
    }

    public List<BannerBean.AdsEntity> getAds() {
        return ads;
    }

    public List<AnnouncementBean.NoticesEntity> getNotices() {
        return notices;
    }
}

package com.elaine.core;

import com.elaine.core.model.AnnouncementBean;
import com.elaine.core.model.BannerBean;
import com.elaine.core.model.HotGoodsBean;

/**
 * Created by elaine on 2018/3/29.
 */

public interface MainPageAction {

    /**
     * 获得首页的轮播图
     * @param method
     * @param callback
     */
    public void getAdvertisementList(String method,ActionCallback<BannerBean> callback);

    /**
     * 获得首页的公告信息
     * @param method
     * @param callback
     */
    public void getHotAnnouncement(String method,ActionCallback<AnnouncementBean> callback);

    /**
     * 获取热门商品
     * @param method
     * @param callback
     */
    public void getHotGoods(String method,ActionCallback<HotGoodsBean> callback);
}

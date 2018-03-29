package com.elaine.core.model;

import java.util.List;

/**
 * Created by elaine on 2018/3/29.
 */

public class HotGoodsBean {
    /**
     * total : 4
     * goods : [{"id":"78","title":"风神AGR66","type":"0","imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/goods/list/AGR66(1)_small.jpeg"},{"id":"80","title":"风神HN08","type":"0","imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/goods/list/HN08(1)_small.jpeg"},{"id":"81","title":"风神AGC28","type":"0","imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/goods/list/AGC28(1)_small.jpeg"},{"id":"97","title":"山玲KTD101","type":"0","imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/goods/list/KTD101(1)_small.jpeg"}]
     */

    private String total;
    /**
     * id : 78
     * title : 风神AGR66
     * type : 0
     * imgUrl : http://120.27.20.124/shuangyi/userfiles/upload/goods/list/AGR66(1)_small.jpeg
     */

    private List<GoodsEntity> goods;

    public void setTotal(String total) {
        this.total = total;
    }

    public void setGoods(List<GoodsEntity> goods) {
        this.goods = goods;
    }

    public String getTotal() {
        return total;
    }

    public List<GoodsEntity> getGoods() {
        return goods;
    }

    public static class GoodsEntity {
        private String id;
        private String title;
        private String type;
        private String imgUrl;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
}

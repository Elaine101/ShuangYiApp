package com.elaine.core.model;

import java.util.List;

/**
 * 轮播图的信息
 */

public class BannerBean {

    /**
     * ads : [{"imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/advertisement/201708281722208966.jpg","type":"2","content":"24"},{"imgUrl":"http://120.27.20.124/shuangyi/userfiles/upload/advertisement/201708281723383617.jpg","type":"2","content":"25"}]
     * total : 2
     */

    private int total;
    /**
     * imgUrl : http://120.27.20.124/shuangyi/userfiles/upload/advertisement/201708281722208966.jpg
     * type : 2
     * content : 24
     */

    private List<AdsEntity> ads;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAds(List<AdsEntity> ads) {
        this.ads = ads;
    }

    public int getTotal() {
        return total;
    }

    public List<AdsEntity> getAds() {
        return ads;
    }

    public static class AdsEntity {
        private String imgUrl;
        private String type;
        private String content;

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }
    }
}

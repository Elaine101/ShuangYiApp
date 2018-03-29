package com.elaine.core.model;

import java.util.List;

/**
 * 公告信息
 */

public class AnnouncementBean {

    /**
     * total : 2
     * notices : [{"id":"13","title":"一车包上线了大家快来啊抢钱了抢抢了强强","content":"<p>尊敬的益車宝用户大家好！自平台2017年9月6号上线以来得到了广大司机用户的认可，在此我们衷心的感谢！为保证益車宝平台健康、持续的发展，维护益車宝平台市场地位以及用户应得利益。现决定自2017年10月1日零点起，益車宝平台系统单笔订单低于1000元，则不支持奖励劵使用。 特此公告！ 益車宝全体员工祝广大用户节日快乐！<\/p> ","addtime":"2017-09-25"},{"id":"11","title":"益車宝上线了","content":"<p style=\"text-align:center\">益車宝上线了<\/p>  <p style=\"text-align:center\"><img alt=\"\" src=\"http://120.27.20.124/shuangyi/userfiles/upload/goods/201709251554534578.jpg\" style=\"height:136px; width:300px\" /><\/p>  <p style=\"text-align:center\">对撒所发送打双打<\/p> ","addtime":"2017-08-30"}]
     */

    private int total;
    /**
     * id : 13
     * title : 一车包上线了大家快来啊抢钱了抢抢了强强
     * content : <p>尊敬的益車宝用户大家好！自平台2017年9月6号上线以来得到了广大司机用户的认可，在此我们衷心的感谢！为保证益車宝平台健康、持续的发展，维护益車宝平台市场地位以及用户应得利益。现决定自2017年10月1日零点起，益車宝平台系统单笔订单低于1000元，则不支持奖励劵使用。 特此公告！ 益車宝全体员工祝广大用户节日快乐！</p>
     * addtime : 2017-09-25
     */

    private List<NoticesEntity> notices;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setNotices(List<NoticesEntity> notices) {
        this.notices = notices;
    }

    public int getTotal() {
        return total;
    }

    public List<NoticesEntity> getNotices() {
        return notices;
    }

    public static class NoticesEntity {
        private String id;
        private String title;
        private String content;
        private String addtime;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getAddtime() {
            return addtime;
        }
    }
}

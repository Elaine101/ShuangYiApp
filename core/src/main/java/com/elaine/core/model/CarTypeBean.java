package com.elaine.core.model;

import java.util.List;

/**
 * Created by elaine on 2018/4/3.
 */

public class CarTypeBean {

    /**
     * total : 1
     * resData : [{"id":"58","name":"丰田坦途5700皮卡"}]
     */

    private String total;
    /**
     * id : 58
     * name : 丰田坦途5700皮卡
     */

    private List<ResDataEntity> resData;

    public void setTotal(String total) {
        this.total = total;
    }

    public void setResData(List<ResDataEntity> resData) {
        this.resData = resData;
    }

    public String getTotal() {
        return total;
    }

    public List<ResDataEntity> getResData() {
        return resData;
    }

    public static class ResDataEntity {
        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}

package com.elaine.core.model;

import java.util.List;

/**
 * Created by elaine on 2018/4/2.
 */

public class CarLevelBean {

    /**
     * total : 21
     * resData : [{"id":"47","name":"集卡车"},{"id":"46","name":"商砼车"},{"id":"45","name":"载重卡车"},{"id":"44","name":"搅拌车"},{"id":"43","name":"载重货车"},{"id":"42","name":"货车"},{"id":"41","name":"轻卡"},{"id":"40","name":"卡车"},{"id":"37","name":"挂车"},{"id":"28","name":"工程车"},{"id":"27","name":"载重车"},{"id":"17","name":"客车"},{"id":"9","name":"专用车"},{"id":"8","name":"电动封闭箱货"},{"id":"7","name":"自卸车"},{"id":"6","name":"牵引车"},{"id":"5","name":"载货车"},{"id":"4","name":"电动轻卡"},{"id":"3","name":"封闭货车"},{"id":"2","name":"微面"},{"id":"1","name":"皮卡"}]
     */

    private String total;
    /**
     * id : 47
     * name : 集卡车
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

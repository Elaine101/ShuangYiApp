package com.elaine.core.model;

import java.util.List;

/**
 * Created by elaine on 2018/4/2.
 */

public class InsuranceCompanyBean {

    /**
     * total : 4
     * resData : [{"id":"23","name":"人保车险"},{"id":"24","name":"大地车险"},{"id":"25","name":"平安车险"},{"id":"26","name":"永安车险"}]
     */

    private int total;
    /**
     * id : 23
     * name : 人保车险
     */

    private List<ResDataEntity> resData;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResData(List<ResDataEntity> resData) {
        this.resData = resData;
    }

    public int getTotal() {
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

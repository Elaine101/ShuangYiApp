package com.elaine.core.model;

import java.util.List;

/**
 * Created by elaine on 2018/4/3.
 */

public class CarBrandBean {

    /**
     * total : 35
     * resData : [{"id":"1","name":"一汽蓝舰","start_letter":"Y"},{"id":"7","name":"铂骏","start_letter":"B"},{"id":"11","name":"长安商用","start_letter":"C"},{"id":"16","name":"重汽王牌","start_letter":"Z"},{"id":"17","name":"开瑞汽车","start_letter":"K"},{"id":"19","name":"黄海汽车","start_letter":"H"},{"id":"23","name":"丰田汽车","start_letter":"F"},{"id":"28","name":"郑州日产","start_letter":"Z"},{"id":"31","name":"金杯","start_letter":"J"},{"id":"36","name":"庆铃","start_letter":"Q"},{"id":"47","name":"东风","start_letter":"D"},{"id":"55","name":"成功汽车","start_letter":"C"},{"id":"63","name":"美国通用","start_letter":"M"},{"id":"64","name":"雪佛兰","start_letter":"X"},{"id":"69","name":"长丰汽车","start_letter":"C"},{"id":"73","name":"江铃汽车","start_letter":"J"},{"id":"74","name":"福迪汽车","start_letter":"F"},{"id":"76","name":"五十铃","start_letter":"W"},{"id":"84","name":"凯马","start_letter":"K"},{"id":"88","name":"上汽大通","start_letter":"S"},{"id":"92","name":"道奇","start_letter":"D"},{"id":"93","name":"江西五十铃","start_letter":"J"},{"id":"100","name":"海格汽车","start_letter":"H"},{"id":"102","name":"大众","start_letter":"D"},{"id":"106","name":"明君汽车","start_letter":"M"},{"id":"109","name":"北京牌","start_letter":"B"},{"id":"112","name":"中兴汽车","start_letter":"Z"},{"id":"119","name":"长城汽车","start_letter":"C"},{"id":"121","name":"卡威","start_letter":"K"},{"id":"122","name":"福特汽车","start_letter":"F"},{"id":"123","name":"广汽吉奥","start_letter":"G"},{"id":"125","name":"江淮皮卡","start_letter":"J"},{"id":"127","name":"江铃集团轻汽","start_letter":"J"},{"id":"130","name":"福田皮卡","start_letter":"F"},{"id":"134","name":"恒天汽车","start_letter":"H"}]
     */

    private int total;
    /**
     * id : 1
     * name : 一汽蓝舰
     * start_letter : Y
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
        private String start_letter;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStart_letter(String start_letter) {
            this.start_letter = start_letter;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getStart_letter() {
            return start_letter;
        }
    }
}

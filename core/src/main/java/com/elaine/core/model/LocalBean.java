package com.elaine.core.model;

/**
 * 保存用户身份，登录成功时返回需要保存在本地方便下次直接进入APP的用户信息
 */
public class LocalBean {

    /**
     * mobile : 13501184658
     * realname : 月饼
     * sex : 1
     * birthday : 2017-10-10
     * car_type : 比亚迪T4载货车
     * car_number : 陕za922d
     * car_buytime : 2017-10-08
     * headimg : http://120.27.20.124/shuangyi/userfiles/upload/user/20180131/201801312010588551.jpg
     * idnum : 340621198512318455
     * insurance_company : 平安车险
     * insurance_time : 2017-10-13
     * addtime : 2017-09-12
     * activate_time : 暂无
     * status : 1
     * pay_password : 14e1b600b1fd579f47433b88e8d85291
     * small_without_password : 0
     * small_without_password_value : null
     * pay_qr_code : 暂无
     * isvip : 0
     * isbig : 0
     * member_card : 73164869
     * large_yue : 0.00
     * msg_remind : 0
     * invited_code : MSN95
     * client_id : 1104a89792a326bb3be
     * token : e937qWzFYnakqP0mKrJK15+cKpUPKXs5QIbqwhPeZy+ec3UQV6r59JTEvHjVGC648ma02g
     */

    private String mobile;
    private String realname;
    private String sex;
    private String birthday;
    private String car_type;
    private String car_number;
    private String car_buytime;
    private String headimg;
    private String idnum;
    private String insurance_company;
    private String insurance_time;
    private String addtime;
    private String activate_time;
    private String status;
    private String pay_password;
    private String small_without_password;
    private String small_without_password_value;
    private String pay_qr_code;
    private String isvip;
    private String isbig;
    private String member_card;
    private String large_yue;
    private String msg_remind;
    private String invited_code;
    private String client_id;
    private String token;

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setCar_buytime(String car_buytime) {
        this.car_buytime = car_buytime;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public void setInsurance_time(String insurance_time) {
        this.insurance_time = insurance_time;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setActivate_time(String activate_time) {
        this.activate_time = activate_time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }

    public void setSmall_without_password(String small_without_password) {
        this.small_without_password = small_without_password;
    }

    public void setSmall_without_password_value(String small_without_password_value) {
        this.small_without_password_value = small_without_password_value;
    }

    public void setPay_qr_code(String pay_qr_code) {
        this.pay_qr_code = pay_qr_code;
    }

    public void setIsvip(String isvip) {
        this.isvip = isvip;
    }

    public void setIsbig(String isbig) {
        this.isbig = isbig;
    }

    public void setMember_card(String member_card) {
        this.member_card = member_card;
    }

    public void setLarge_yue(String large_yue) {
        this.large_yue = large_yue;
    }

    public void setMsg_remind(String msg_remind) {
        this.msg_remind = msg_remind;
    }

    public void setInvited_code(String invited_code) {
        this.invited_code = invited_code;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRealname() {
        return realname;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCar_type() {
        return car_type;
    }

    public String getCar_number() {
        return car_number;
    }

    public String getCar_buytime() {
        return car_buytime;
    }

    public String getHeadimg() {
        return headimg;
    }

    public String getIdnum() {
        return idnum;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public String getInsurance_time() {
        return insurance_time;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getActivate_time() {
        return activate_time;
    }

    public String getStatus() {
        return status;
    }

    public String getPay_password() {
        return pay_password;
    }

    public String getSmall_without_password() {
        return small_without_password;
    }

    public String getSmall_without_password_value() {
        return small_without_password_value;
    }

    public String getPay_qr_code() {
        return pay_qr_code;
    }

    public String getIsvip() {
        return isvip;
    }

    public String getIsbig() {
        return isbig;
    }

    public String getMember_card() {
        return member_card;
    }

    public String getLarge_yue() {
        return large_yue;
    }

    public String getMsg_remind() {
        return msg_remind;
    }

    public String getInvited_code() {
        return invited_code;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "LocalBean{" +
                "mobile='" + mobile + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

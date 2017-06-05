package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/5 17: 55
 */
//车内任务详情
public class AssignCarBean implements Serializable{

    /**
     * result : 1
     * message : 请求成功！
     * data : [{"subscriber_time":"0","note":"","user_name":"刘签","u_phone":"18790158765","order_number":"201705241451113694660136","pay_time":"0000-00-00 00:00:00","user_car_type":"奇瑞","license_number":"沪AN1234","car_age":"2016,8","car_type":"客车"}]
     */

    private int result;
    private String message;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * subscriber_time : 0
         * note :
         * user_name : 刘签
         * u_phone : 18790158765
         * order_number : 201705241451113694660136
         * pay_time : 0000-00-00 00:00:00
         * user_car_type : 奇瑞
         * license_number : 沪AN1234
         * car_age : 2016,8
         * car_type : 客车
         */

        private String subscriber_time;
        private String note;
        private String user_name;
        private String u_phone;
        private String order_number;
        private String pay_time;
        private String user_car_type;
        private String license_number;
        private String car_age;
        private String car_type;

        public String getSubscriber_time() {
            return subscriber_time;
        }

        public void setSubscriber_time(String subscriber_time) {
            this.subscriber_time = subscriber_time;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getU_phone() {
            return u_phone;
        }

        public void setU_phone(String u_phone) {
            this.u_phone = u_phone;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getUser_car_type() {
            return user_car_type;
        }

        public void setUser_car_type(String user_car_type) {
            this.user_car_type = user_car_type;
        }

        public String getLicense_number() {
            return license_number;
        }

        public void setLicense_number(String license_number) {
            this.license_number = license_number;
        }

        public String getCar_age() {
            return car_age;
        }

        public void setCar_age(String car_age) {
            this.car_age = car_age;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }
    }
}

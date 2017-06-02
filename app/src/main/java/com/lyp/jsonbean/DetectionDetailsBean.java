package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/2 18: 11
 */
//检测
public class DetectionDetailsBean implements Serializable{

    /**
     * result : 1
     * message : 请求成功！
     * data : [{"user_name":"cui","u_phone":"18310120383","u_addres":"车站北路","point_position":"客厅","bit_type":"甲醛、苯、TVOC","covered_area":"22","pay_time":"0000-00-00 00:00:00","order_number":"20170522093982179801133P1","detection_type":"初检","room_nums":"1"}]
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
         * user_name : cui
         * u_phone : 18310120383
         * u_addres : 车站北路
         * point_position : 客厅
         * bit_type : 甲醛、苯、TVOC
         * covered_area : 22
         * pay_time : 0000-00-00 00:00:00
         * order_number : 20170522093982179801133P1
         * detection_type : 初检
         * room_nums : 1
         */

        private String user_name;
        private String u_phone;
        private String u_addres;
        private String point_position;
        private String bit_type;
        private String covered_area;
        private String pay_time;
        private String order_number;
        private String detection_type;
        private String room_nums;

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

        public String getU_addres() {
            return u_addres;
        }

        public void setU_addres(String u_addres) {
            this.u_addres = u_addres;
        }

        public String getPoint_position() {
            return point_position;
        }

        public void setPoint_position(String point_position) {
            this.point_position = point_position;
        }

        public String getBit_type() {
            return bit_type;
        }

        public void setBit_type(String bit_type) {
            this.bit_type = bit_type;
        }

        public String getCovered_area() {
            return covered_area;
        }

        public void setCovered_area(String covered_area) {
            this.covered_area = covered_area;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getDetection_type() {
            return detection_type;
        }

        public void setDetection_type(String detection_type) {
            this.detection_type = detection_type;
        }

        public String getRoom_nums() {
            return room_nums;
        }

        public void setRoom_nums(String room_nums) {
            this.room_nums = room_nums;
        }
    }
}

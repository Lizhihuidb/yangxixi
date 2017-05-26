package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/25 18: 54
 */
//抢单详情
public class JLSingeDetailsBean implements Serializable{

    /**
     * result : 1
     * message : 请求成功！
     * data : [{"user_name":"cui","u_phone":"18310120383","u_addres":"车站北路","point_position":"客厅","room_nums":"1","covered_area":"22","pay_time":"0000-00-00 00:00:00","order_number":"20170522093982179801133"}]
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
         * room_nums : 1
         * covered_area : 22
         * pay_time : 0000-00-00 00:00:00
         * order_number : 20170522093982179801133
         */

        private String user_name;
        private String u_phone;
        private String u_addres;
        private String point_position;
        private String room_nums;
        private String covered_area;
        private String pay_time;
        private String order_number;

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

        public String getRoom_nums() {
            return room_nums;
        }

        public void setRoom_nums(String room_nums) {
            this.room_nums = room_nums;
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
    }
}

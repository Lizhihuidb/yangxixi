package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/1 17: 32
 */
//室内
public class JLSingeIndoorBean implements Serializable{


    /**
     * result : 1
     * message : 已经到底了哦！
     * counts : 4
     * next_page : 0
     * data : [{"o_id":"235","server_type":"室内空气净化服务","expected_time":"2017年6月3日19~20时","select_server8":"2","u_addres":"上海市浦东新区盛夏路666号","covered_area":"30","govern_price":0,"square_meter_price":0,"room_nums":"2","cash_deposit":0,"bit_num":"26","reminder_type":1},{"o_id":"234","server_type":"室内空气净化服务","expected_time":"2017年6月4日15~17时","select_server8":"6","u_addres":"上海市虹口区车站北路403弄6号楼603","covered_area":"100","govern_price":0,"square_meter_price":0,"room_nums":"6","cash_deposit":0,"bit_num":"26","reminder_type":1},{"o_id":"233","server_type":"室内空气净化服务","expected_time":"2017年6月4日11~13时","select_server8":"2","u_addres":"上海市宝山区沪太路789弄10号楼809","covered_area":"20","govern_price":0,"square_meter_price":0,"room_nums":"2","cash_deposit":0,"bit_num":"26","reminder_type":1},{"o_id":"148","server_type":"室内空气净化服务","expected_time":"2017年5月31日15~17时","select_server8":"3","u_addres":"上海市宝山区沪太路789弄10号楼809","covered_area":"70","govern_price":0,"square_meter_price":0,"room_nums":"3","cash_deposit":0,"bit_num":"26","reminder_type":1}]
     */

    private int result;
    private String message;
    private int counts;
    private int next_page;
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

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getNext_page() {
        return next_page;
    }

    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JLSingeIndoorBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", counts=" + counts +
                ", next_page=" + next_page +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * o_id : 235
         * server_type : 室内空气净化服务
         * expected_time : 2017年6月3日19~20时
         * select_server8 : 2
         * u_addres : 上海市浦东新区盛夏路666号
         * covered_area : 30
         * govern_price : 0
         * square_meter_price : 0
         * room_nums : 2
         * cash_deposit : 0
         * bit_num : 26
         * reminder_type : 1
         */

        private String o_id;
        private String server_type;
        private String expected_time;
        private String select_server8;
        private String u_addres;
        private String covered_area;
        private String govern_price;
        private String square_meter_price;
        private String room_nums;
        private int cash_deposit;
        private String bit_num;
        private int reminder_type;

        public String getO_id() {
            return o_id;
        }

        public void setO_id(String o_id) {
            this.o_id = o_id;
        }

        public String getServer_type() {
            return server_type;
        }

        public void setServer_type(String server_type) {
            this.server_type = server_type;
        }

        public String getExpected_time() {
            return expected_time;
        }

        public void setExpected_time(String expected_time) {
            this.expected_time = expected_time;
        }

        public String getSelect_server8() {
            return select_server8;
        }

        public void setSelect_server8(String select_server8) {
            this.select_server8 = select_server8;
        }

        public String getU_addres() {
            return u_addres;
        }

        public void setU_addres(String u_addres) {
            this.u_addres = u_addres;
        }

        public String getCovered_area() {
            return covered_area;
        }

        public void setCovered_area(String covered_area) {
            this.covered_area = covered_area;
        }

        public String getGovern_price() {
            return govern_price;
        }

        public void setGovern_price(String govern_price) {
            this.govern_price = govern_price;
        }

        public String getSquare_meter_price() {
            return square_meter_price;
        }

        public void setSquare_meter_price(String square_meter_price) {
            this.square_meter_price = square_meter_price;
        }

        public String getRoom_nums() {
            return room_nums;
        }

        public void setRoom_nums(String room_nums) {
            this.room_nums = room_nums;
        }

        public int getCash_deposit() {
            return cash_deposit;
        }

        public void setCash_deposit(int cash_deposit) {
            this.cash_deposit = cash_deposit;
        }

        public String getBit_num() {
            return bit_num;
        }

        public void setBit_num(String bit_num) {
            this.bit_num = bit_num;
        }

        public int getReminder_type() {
            return reminder_type;
        }

        public void setReminder_type(int reminder_type) {
            this.reminder_type = reminder_type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "o_id='" + o_id + '\'' +
                    ", server_type='" + server_type + '\'' +
                    ", expected_time='" + expected_time + '\'' +
                    ", select_server8='" + select_server8 + '\'' +
                    ", u_addres='" + u_addres + '\'' +
                    ", covered_area='" + covered_area + '\'' +
                    ", govern_price=" + govern_price +
                    ", square_meter_price=" + square_meter_price +
                    ", room_nums='" + room_nums + '\'' +
                    ", cash_deposit=" + cash_deposit +
                    ", bit_num='" + bit_num + '\'' +
                    ", reminder_type=" + reminder_type +
                    '}';
        }
    }
}

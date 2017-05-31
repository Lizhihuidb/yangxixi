package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/25 18: 48
 */

//抢单(检测)
public class JlSingeDetectionBean implements Serializable{
    /**
     * result : 1
     * message : 已经到底了哦！
     * counts : 2
     * next_page : 0
     * data : [{"o_id":"157","server_type":"第三方检测服务","expected_time":"2017年6月5日15~17时","detection_type":"检测","detection_point":"甲醛、苯、TVOC","select_server8":"1","u_addres":"dsfdsfsd","room_nums":"1","detection_price":0,"cash_deposit":1,"reminder_type":0,"bit_num":"37"},{"o_id":"156","server_type":"第三方检测服务","expected_time":"2017年6月5日13~15时","detection_type":"检测","detection_point":"甲醛、苯、TVOC","select_server8":"1","u_addres":"dsfdsfsd","room_nums":"1","detection_price":0,"cash_deposit":1,"reminder_type":0,"bit_num":"37"}]
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
        return "JlSingeDetectionBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", counts=" + counts +
                ", next_page=" + next_page +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * o_id : 157
         * server_type : 第三方检测服务
         * expected_time : 2017年6月5日15~17时
         * detection_type : 检测
         * detection_point : 甲醛、苯、TVOC
         * select_server8 : 1
         * u_addres : dsfdsfsd
         * room_nums : 1
         * detection_price : 0
         * cash_deposit : 1
         * reminder_type : 0
         * bit_num : 37
         */

        private String o_id;
        private String server_type;
        private String expected_time;
        private String detection_type;
        private String detection_point;
        private String select_server8;
        private String u_addres;
        private String room_nums;
        private String detection_price;
        private int cash_deposit;
        private int reminder_type;
        private String bit_num;

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

        public String getDetection_type() {
            return detection_type;
        }

        public void setDetection_type(String detection_type) {
            this.detection_type = detection_type;
        }

        public String getDetection_point() {
            return detection_point;
        }

        public void setDetection_point(String detection_point) {
            this.detection_point = detection_point;
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

        public String getRoom_nums() {
            return room_nums;
        }

        public void setRoom_nums(String room_nums) {
            this.room_nums = room_nums;
        }

        public String getDetection_price() {
            return detection_price;
        }

        public void setDetection_price(String detection_price) {
            this.detection_price = detection_price;
        }

        public int getCash_deposit() {
            return cash_deposit;
        }

        public void setCash_deposit(int cash_deposit) {
            this.cash_deposit = cash_deposit;
        }

        public int getReminder_type() {
            return reminder_type;
        }

        public void setReminder_type(int reminder_type) {
            this.reminder_type = reminder_type;
        }

        public String getBit_num() {
            return bit_num;
        }

        public void setBit_num(String bit_num) {
            this.bit_num = bit_num;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "o_id='" + o_id + '\'' +
                    ", server_type='" + server_type + '\'' +
                    ", expected_time='" + expected_time + '\'' +
                    ", detection_type='" + detection_type + '\'' +
                    ", detection_point='" + detection_point + '\'' +
                    ", select_server8='" + select_server8 + '\'' +
                    ", u_addres='" + u_addres + '\'' +
                    ", room_nums='" + room_nums + '\'' +
                    ", detection_price='" + detection_price + '\'' +
                    ", cash_deposit=" + cash_deposit +
                    ", reminder_type=" + reminder_type +
                    ", bit_num='" + bit_num + '\'' +
                    '}';
        }
    }
}

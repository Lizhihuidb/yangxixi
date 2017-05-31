package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/31 16: 14
 */
//车内空气净化服务
public class JLCarBean implements Serializable {

    /**
     * result : 1
     * message : 已经到底了哦！
     * counts : 3
     * next_page : 0
     * data : [{"o_id":"168","server_type":"车内空气净化服务","expected_time":"2017年6月4日13~15时","shop_car_type":"客车","license_number":"sdfsdf","car_type":"sdfds"},{"o_id":"167","server_type":"车内空气净化服务","expected_time":"2017年6月5日17~19时","shop_car_type":"客车","license_number":"sdfdsf","car_type":"sdfdsf"},{"o_id":"166","server_type":"车内空气净化服务","expected_time":"2017年6月4日11~13时","shop_car_type":"跑车","license_number":"fdssdfsdf","car_type":"dfssdf"}]
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
        return "JLCarBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", counts=" + counts +
                ", next_page=" + next_page +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * o_id : 168
         * server_type : 车内空气净化服务
         * expected_time : 2017年6月4日13~15时
         * shop_car_type : 客车
         * license_number : sdfsdf
         * car_type : sdfds
         */

        private String o_id;
        private String server_type;
        private String expected_time;
        private String shop_car_type;
        private String license_number;
        private String car_type;

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

        public String getShop_car_type() {
            return shop_car_type;
        }

        public void setShop_car_type(String shop_car_type) {
            this.shop_car_type = shop_car_type;
        }

        public String getLicense_number() {
            return license_number;
        }

        public void setLicense_number(String license_number) {
            this.license_number = license_number;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "o_id='" + o_id + '\'' +
                    ", server_type='" + server_type + '\'' +
                    ", expected_time='" + expected_time + '\'' +
                    ", shop_car_type='" + shop_car_type + '\'' +
                    ", license_number='" + license_number + '\'' +
                    ", car_type='" + car_type + '\'' +
                    '}';
        }
    }
}

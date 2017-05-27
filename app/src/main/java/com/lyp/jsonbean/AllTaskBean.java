package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/25 14: 20
 * QQ:985478538
 */

public class AllTaskBean implements Serializable{
    /**
     * result : 1
     * message : 还可以加载哦！
     * counts : 65
     * next_page : 2
     * data : [{"o_id":"1","user_name":"cui","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170522093982179801133","distribute_type":"0","subscriber_type":"0"},{"o_id":"2","user_name":"cui","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170522093982179801133P1","distribute_type":"0","subscriber_type":"0"},{"o_id":"3","user_name":"cui","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170522093982179801133P12","distribute_type":"0","subscriber_type":"0"},{"o_id":"4","user_name":"梅琰培","u_phone":"13761809104","pay_time":"0000-00-00 00:00:00","order_number":"20170522094487461530136","distribute_type":"0","subscriber_type":"0"},{"o_id":"5","user_name":"Sunsh","u_phone":"13690457890","pay_time":"0000-00-00 00:00:00","order_number":"20170522094488479139136","distribute_type":"0","subscriber_type":"0"},{"o_id":"6","user_name":"王菲","u_phone":"13567890145","pay_time":"0000-00-00 00:00:00","order_number":"20170522094589516167136","distribute_type":"0","subscriber_type":"0"},{"o_id":"7","user_name":"刘签","u_phone":"18790158765","pay_time":"0000-00-00 00:00:00","order_number":"20170522094591541734136","distribute_type":"0","subscriber_type":"0"},{"o_id":"8","user_name":"王富贵","u_phone":"13987310953","pay_time":"0000-00-00 00:00:00","order_number":"20170522094698569177136","distribute_type":"0","subscriber_type":"0"},{"o_id":"9","user_name":"梅琰培","u_phone":"13761809104","pay_time":"0000-00-00 00:00:00","order_number":"20170522094856728411136","distribute_type":"0","subscriber_type":"0"},{"o_id":"10","user_name":"Sunsh","u_phone":"13690457890","pay_time":"0000-00-00 00:00:00","order_number":"201705220949112768528136","distribute_type":"1","subscriber_type":"1"}]
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
        return "AllTaskBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", counts=" + counts +
                ", next_page=" + next_page +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * o_id : 1
         * user_name : cui
         * u_phone : 18310120383
         * pay_time : 0000-00-00 00:00:00
         * order_number : 20170522093982179801133
         * distribute_type : 0
         * subscriber_type : 0
         */

        private String o_id;
        private String user_name;
        private String u_phone;
        private String pay_time;
        private String order_number;
        private String distribute_type;
        private String subscriber_type;

        public String getO_id() {
            return o_id;
        }

        public void setO_id(String o_id) {
            this.o_id = o_id;
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

        public String getDistribute_type() {
            return distribute_type;
        }

        public void setDistribute_type(String distribute_type) {
            this.distribute_type = distribute_type;
        }

        public String getSubscriber_type() {
            return subscriber_type;
        }

        public void setSubscriber_type(String subscriber_type) {
            this.subscriber_type = subscriber_type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "o_id='" + o_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", u_phone='" + u_phone + '\'' +
                    ", pay_time='" + pay_time + '\'' +
                    ", order_number='" + order_number + '\'' +
                    ", distribute_type='" + distribute_type + '\'' +
                    ", subscriber_type='" + subscriber_type + '\'' +
                    '}';
        }

    }
}

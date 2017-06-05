package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
//未分配
public class NofenpeiBean implements Serializable{


    /**
     * result : 1
     * message : 已经到底了哦！
     * counts : 2
     * next_page : 0
     * data : [{"o_id":"3","user_name":"崔市磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"12","user_name":"崔市sdf磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"3","user_name":"崔市磊vcx","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"12","user_name":"崔erw市磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"3","user_name":"崔市cvbc磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"12","user_name":"崔市cvbcv磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"3","user_name":"崔市ertre磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"12","user_name":"assdf崔市磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"3","user_name":"cvbxcv崔市磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"},{"o_id":"12","user_name":"xcbx崔市磊","u_phone":"18310120383","pay_time":"0000-00-00 00:00:00","order_number":"20170426135782260635134P12"}]
     */

    private int result;
    private String message;
    private String counts;
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

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
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

    public static class DataBean implements Serializable{
        /**
         * o_id : 3
         * user_name : 崔市磊
         * u_phone : 18310120383
         * pay_time : 0000-00-00 00:00:00
         * order_number : 20170426135782260635134P12
         */

        private String o_id;
        private String user_name;
        private String u_phone;
        private String pay_time;
        private String order_number;

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
    }
}

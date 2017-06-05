package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
//我的任务(所有任务)
public class MAllTaskBean implements Serializable{


    /**
     * result : 1
     * message : 已查询全部数据!
     * count : 1
     * data : [{"u_name":"崔市磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":0,"subscriber_isdate":1},{"u_name":"崔市fsda磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":1,"subscriber_isdate":0},{"u_name":"崔市sfd磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":0,"subscriber_isdate":0},{"u_name":"崔市safd磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":1,"subscriber_isdate":1},{"u_name":"崔市fsd磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":0,"subscriber_isdate":0},{"u_name":"崔市fas磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":1,"subscriber_isdate":0},{"u_name":"崔市afs磊","id":"5","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00","subscriber_time":"2017-05-05 16:25:49","subscriber_idold":0,"subscriber_isdate":1}]
     */

    private int result;
    private String message;
    private String count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * u_name : 崔市磊
         * id : 5
         * distribute_type : 1
         * subscriber_type : 0
         * pay_time : 0000-00-00 00:00:00
         * subscriber_time : 2017-05-05 16:25:49
         * subscriber_idold : 0
         * subscriber_isdate : 1
         */

        private String u_name;
        private String id;
        private String distribute_type;
        private String subscriber_type;
        private String pay_time;
        private String subscriber_time;
        private String subscriber_idold;
        private String subscriber_isdate;

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getSubscriber_time() {
            return subscriber_time;
        }

        public void setSubscriber_time(String subscriber_time) {
            this.subscriber_time = subscriber_time;
        }

        public String getSubscriber_idold() {
            return subscriber_idold;
        }

        public void setSubscriber_idold(String subscriber_idold) {
            this.subscriber_idold = subscriber_idold;
        }

        public String getSubscriber_isdate() {
            return subscriber_isdate;
        }

        public void setSubscriber_isdate(String subscriber_isdate) {
            this.subscriber_isdate = subscriber_isdate;
        }
    }
}

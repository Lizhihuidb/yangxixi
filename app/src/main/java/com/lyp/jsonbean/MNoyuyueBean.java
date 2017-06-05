package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class MNoyuyueBean implements Serializable{


    /**
     * result : 1
     * message : 已查询全部数据!
     * count : 1
     * data : [{"u_name":"崔市磊","id":"6","distribute_type":"1","subscriber_type":"0","pay_time":"0000-00-00 00:00:00"}]
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
         * id : 6
         * distribute_type : 1
         * subscriber_type : 0
         * pay_time : 0000-00-00 00:00:00
         */

        private String u_name;
        private String id;
        private String distribute_type;
        private String subscriber_type;
        private String pay_time;

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
    }
}

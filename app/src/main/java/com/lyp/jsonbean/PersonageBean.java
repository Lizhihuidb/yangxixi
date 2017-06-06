package com.lyp.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/6 18: 36
 */
//个人信息
public class PersonageBean implements Serializable{

    /**
     * result : 1
     * message : 用戶信息
     * data : [{"id":"8","c_id":"10","user":"zhilan_04","pwd":"123456","set_pwd":"123456","manager":"0","login":"0","feedback":"","company_name":"上海国齐检测技术有限公司(国齐检测)","is_show":"1","server_select":"第三方检测服务","server_address":"","company_grade":"5","car_address":""}]
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
         * id : 8
         * c_id : 10
         * user : zhilan_04
         * pwd : 123456
         * set_pwd : 123456
         * manager : 0
         * login : 0
         * feedback :
         * company_name : 上海国齐检测技术有限公司(国齐检测)
         * is_show : 1
         * server_select : 第三方检测服务
         * server_address :
         * company_grade : 5
         * car_address :
         */

        private String id;
        private String c_id;
        private String user;
        private String pwd;
        private String set_pwd;
        private String manager;
        private String login;
        private String feedback;
        private String company_name;
        private String is_show;
        private String server_select;
        private String server_address;
        private String company_grade;
        private String car_address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSet_pwd() {
            return set_pwd;
        }

        public void setSet_pwd(String set_pwd) {
            this.set_pwd = set_pwd;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getServer_select() {
            return server_select;
        }

        public void setServer_select(String server_select) {
            this.server_select = server_select;
        }

        public String getServer_address() {
            return server_address;
        }

        public void setServer_address(String server_address) {
            this.server_address = server_address;
        }

        public String getCompany_grade() {
            return company_grade;
        }

        public void setCompany_grade(String company_grade) {
            this.company_grade = company_grade;
        }

        public String getCar_address() {
            return car_address;
        }

        public void setCar_address(String car_address) {
            this.car_address = car_address;
        }
    }
}

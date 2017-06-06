package com.tjl.yangxixi.api;

import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.AssignCarBean;
import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.DetectionDetailsBean;
import com.lyp.jsonbean.IndependentBean;
import com.lyp.jsonbean.IndoorDetailsBean;
import com.lyp.jsonbean.JLCarBean;
import com.lyp.jsonbean.JLSingeIndoorBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.lyp.jsonbean.LoginBean;
import com.lyp.jsonbean.MAllTaskBean;
import com.lyp.jsonbean.MNoyuyueBean;
import com.lyp.jsonbean.MTodayBean;
import com.lyp.jsonbean.MYesyuyueBean;
import com.lyp.jsonbean.NoOrderBean;
import com.lyp.jsonbean.NofenpeiBean;
import com.lyp.jsonbean.PersonageBean;
import com.lyp.jsonbean.YesOrderBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者:ChenXi 时间:2017/5/23 12: 00
 * QQ:985478538
 */

public interface YangxixiApi {

//     public String APP_URL = "http://192.168.1.41/api/index.php/api/";

        public String APP_URL = "http://xqq.0102003.com/api/";


    //登录
     @FormUrlEncoded
//    @POST("app/login")
    @POST("api.php")
     Call<LoginBean> getLogin(
             @Field("userName") String userName,
             @Field("userPwd") String userPwd);

    //抢单(检测)
    @GET("app/robOrder")
    Call<JlSingeDetectionBean> getSingDetection(
            @Query("server_select") String server_select,
            @Query("page") int page,
            @Query("c_id") String c_id);

    //车内
//    @GET("app/robOrder")
    @GET("jiedancar.php")
    Call<JLCarBean> getSingCar(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);

    //室内
    @GET("app/robOrder")
    Call<JLSingeIndoorBean> getSingIndoor(
            @Query("server_select") String server_select,
            @Query("page") int page,
            @Query("c_id") String c_id);

    //接单详情(车内)
//    @GET("app/orderDetails")
    @GET("carxq.php")
    Call<CarOrdersDetailsBean> getCarOrders(
            @Query("o_id") String o_id);

    //抢单(检测)
    @GET("app/orderDetails")
    Call<DetectionDetailsBean> getDetectionOrders(
            @Query("o_id") String o_id);

    //抢单(室内)
    @GET("app/orderDetails")
    Call<IndoorDetailsBean> getIndoorOrders(
            @Query("o_id") String o_id);

    //所有任务
//    @GET("app/allMission")
    @GET("alltask.php")
    Call<AllTaskBean> getAllTask(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);

    //未分配
//    @GET("app/undistributed")
    @GET("nofenpei.php")
    Call<NofenpeiBean> getNoTask(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);

    //已预约
//    @GET("app/subscriber")
    @GET("yiyuyue.php")
    Call<YesOrderBean> getYesOrder(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);

    //未预约
//    @GET("app/unsubscriber")
    @GET("noyuyue.php")
    Call<NoOrderBean> getNoOrder(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);

    //分配任务(车内)详情
    @GET("app/staff_orderDetails ")
    Call<AssignCarBean> getAssignCar(
            @Query("o_id") String o_id);

    //我的任务(全部任务)
    @GET("app/staff_allMission")
//    @GET("malltask.php")
    Call<MAllTaskBean> getMAlltask(
            @Query("a_id") String a_id,
            @Query("page") int page);

    //今日任务
    @GET("app/staff_dateMission")
//    @GET("today.php")
    Call<MTodayBean> getMToday(
            @Query("a_id") String a_id,
            @Query("page") int page);

    //已预约(我的任务)
    @GET("app/staff_subscriber")
//    @GET("myiyuyue.php")
    Call<MYesyuyueBean> getMYestask(
            @Query("a_id") String a_id,
            @Query("page") int page);

    //未预约(我的任务)
    @GET("app/staff_unsubscriber")
//    @GET("mnoyuyue.php")
    Call<MNoyuyueBean> getMNotask(
            @Query("a_id") String a_id,
            @Query("page") int page);

    //自主分配
    @GET("app/distribute")
    Call<IndependentBean> getIndepen(
            @Query("id") String id,
            @Query("ids") String ids);

    //个人信息
    @GET("Order/userMessage")
    Call<PersonageBean> getPerson(
            @Query("aid") String aid);

}

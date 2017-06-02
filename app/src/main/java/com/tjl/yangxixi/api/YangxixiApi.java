package com.tjl.yangxixi.api;

import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.JLCarBean;
import com.lyp.jsonbean.JLSingeIndoorBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.lyp.jsonbean.LoginBean;

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

     public String APP_URL = "http://192.168.1.41/api/index.php/api/";


    //登录
     @FormUrlEncoded
    @POST("app/login")
//    @POST("api.php")
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
    @GET("app/robOrder")
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


    //所有任务
    @GET("app/allMission")
    Call<AllTaskBean> getAllTask(
            @Query("c_id") String c_id,
            @Query("server_select") String server_select,
            @Query("page") int page);


    //接单详情(车内)/抢单(室内、第三方检测)
    @GET("app/orderDetails")
    Call<CarOrdersDetailsBean> getCarOrders(
            @Query("o_id") String o_id);

}

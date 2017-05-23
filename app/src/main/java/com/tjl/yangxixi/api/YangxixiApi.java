package com.tjl.yangxixi.api;

import com.tjl.yangxixi.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者:ChenXi 时间:2017/5/23 12: 00
 * QQ:985478538
 */

public interface YangxixiApi {

     public String APP_URL = "http://192.168.1.41/api/index.php/api/";


     @FormUrlEncoded
    @POST("app/login")
//    @POST("api.php")
     Call<LoginBean> getLogin(
             @Field("userName") String userName,
             @Field("userPwd") String userPwd);

}

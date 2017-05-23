package com.tjl.yangxixi.activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.api.YangxixiApi;
import com.tjl.yangxixi.bean.LoginBean;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoggingActivity extends Activity implements OnClickListener{

    private TextView mLoginimg;
    private EditText mUserName;
    private EditText mUserPwd;
    private Button mLogin;
    private TextView mForgetPwd;
    private String userName;
    private String userPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        initview();
        Log.i("tag", "aaaaaaaaaaaaaaaaaaaa");
        start();

    }


    private void initview(){
        mLoginimg = (TextView) findViewById(R.id.start_loginimg);
        mUserName = (EditText) findViewById(R.id.et_loginuserName);
        mUserPwd = (EditText) findViewById(R.id.et_loginuserpwd);
        mLogin = (Button) findViewById(R.id.btn_logins);
        mForgetPwd = (TextView) findViewById(R.id.tv_loginforgetPwd);
        mUserName.setOnClickListener(this);
        mUserPwd.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mForgetPwd.setOnClickListener(this);



    }

    //启动图
    private void start(){
        /**
         //		 * 五秒后隐藏启动图/显示登陆页
         //		 */
        int status = getIntent().getIntExtra("status", 0);
        if (status == 1) {
            mLoginimg.setVisibility(View.GONE);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mLoginimg.setVisibility(View.GONE);
                }
            }, 1000);
        }
    }


    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.btn_logins:
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
              break;
        }
    }

    public void login() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YangxixiApi.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userName = mUserName.getText().toString();
 //       userPwd = Integer.parseInt(mUserPwd.getText().toString());
        userPwd = mUserPwd.getText().toString();
        YangxixiApi github = retrofit.create(YangxixiApi.class);
        Call<LoginBean> call = github.getLogin(userName, userPwd);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
//                    LoginBean bean = response.body();
                    Log.i("tag", "成功"+response.body()+"");
                   List<LoginBean.DataBean> data=response.body().getData();
                 Log.i("tag", ""+data.size());
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.i("tag","失败"+t.getMessage());
            }
        });

    }
}

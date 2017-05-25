package com.tjl.yangxixi.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.api.YangxixiApi;
import com.lyp.jsonbean.LoginBean;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoggingActivity extends FragmentActivity implements OnClickListener{
    private TextView mLoginimg;
    private EditText mUserName;
    private EditText mUserPwd;
    private Button mLogin;
    private TextView mForgetPwd;
    private String userName;
    private String userPwd;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        initview();
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
                    if (isNull()) {
                        login();
                    }
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
        YangxixiApi github = retrofit.create(YangxixiApi.class);
        Call<LoginBean> call = github.getLogin(userName, userPwd);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean bean = response.body();
                if (bean.getResult() == 1) {
                    Toast.makeText(LoggingActivity.this,""+response.message(),Toast.LENGTH_SHORT).show();
                    List<LoginBean.DataBean> dataBeen = bean.getData();
//                        if (dataBeen.get(0).getManager().equals("0")){
//                            Log.i("tag","管理员");
//                            setDefaultFragment(HomeFragment.newInstance(dataBeen.get(0)));
//                        }
//                        else if(dataBeen.get(0).getManager().equals("1")){
//                            Log.i("tag","普通成员");
//                            setDefaultFragment(GWHomeFragment.newInstance(dataBeen.get(0)));
//                        }
                    Intent intent = new Intent(LoggingActivity.this,MainActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("dataBean", dataBeen.get(0));
                    intent.putExtras(args);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoggingActivity.this, ""+response.message(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.i("tag","失败"+t.getMessage());
            }
        });
    }

    //判断用户名和密码是否为空
    boolean isNull(){
        userName = mUserName.getText().toString();
        userPwd = mUserPwd.getText().toString();
        //判断用户名是否为空
        if (userName.isEmpty()){
            Toast.makeText(LoggingActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return false;
        } else if(userPwd.isEmpty()){ //判断密码是否为空
            Toast.makeText(LoggingActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    //跳转到相对应的Fragment
//    private void setDefaultFragment(Fragment fragment){
//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
//        ft.add(R.id.frame, fragment);
//        //transaction.addToBackStack(null);
//        ft.commit();
//    }
}

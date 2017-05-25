package com.tjl.yangxixi.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.fragment.HomeFragment;
import com.tjl.yangxixi.fragment.JlMyFragment;
import com.tjl.yangxixi.fragment.JlTrailReportFragment;
import com.tjl.yangxixi.fragment.MissionManagerFragment;
import com.tjl.yangxixi.fragment.SingeFragment;

import java.util.ArrayList;
import java.util.List;

//经理的首页
public class JLHomeActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private List<Fragment> fragments;
    private HomeFragment homeFragment;
    private SingeFragment singeFragment;
    private MissionManagerFragment missionManagerFragment;
    private JlTrailReportFragment jlTrailReportFragment;
    private JlMyFragment jlMyFragment;
    Button mHome,mSinge,mTask,mReport,mMy;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttom);
        initview();
    }

    private void initview(){
        fragments = new ArrayList<Fragment>();
        homeFragment = new HomeFragment();
        singeFragment = new SingeFragment();
        missionManagerFragment = new MissionManagerFragment();
        jlTrailReportFragment = new JlTrailReportFragment();
        jlMyFragment = new JlMyFragment();
        fragments.add(homeFragment);
        fragments.add(singeFragment);
        fragments.add(missionManagerFragment);
        fragments.add(jlTrailReportFragment);
        fragments.add(jlMyFragment);
        mHome = (Button) findViewById(R.id.jl_home);
        mSinge = (Button) findViewById(R.id.jl_singe);
        mTask = (Button) findViewById(R.id.jl_task);
        mReport = (Button) findViewById(R.id.jl_report);
        mMy = (Button) findViewById(R.id.jl_my);
        mViewPager = (ViewPager) findViewById(R.id.vp_mian);
        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(this);
        mHome.setOnClickListener(this);
        mSinge.setOnClickListener(this);
        mTask.setOnClickListener(this);
        mReport.setOnClickListener(this);
        mViewPager.setOnClickListener(this);

    }

    //自定义适配器
    class MyFragmentAdapter extends FragmentPagerAdapter {
        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            return fragments.get(index);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    //颜色
    private void setTxtColor(TextView endTxt) {
        switch (endTxt.getId()) {
            //首页
            case R.id.jl_home:
                mHome.setTextColor(getResources().getColor(R.color.bg_title));
                mHome.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.j_home_page, 0, 0);
                mSinge.setTextColor(getResources().getColor(R.color.black));
                mSinge.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_grab_a_single, 0, 0);
                mTask.setTextColor(getResources().getColor(R.color.black));
                mTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_task, 0, 0);
                mReport.setTextColor(getResources().getColor(R.color.black));
                mReport.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_the_report, 0, 0);
                mMy.setTextColor(getResources().getColor(R.color.black));
                mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_my, 0, 0);
                break;
            //抢单
            case R.id.jl_singe:
                mHome.setTextColor(getResources().getColor(R.color.black));
                mHome.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_home_page, 0, 0);
                mSinge.setTextColor(getResources().getColor(R.color.bg_title));
                mSinge.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.j_grab_a_single, 0, 0);
                mTask.setTextColor(getResources().getColor(R.color.black));
                mTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_task, 0, 0);
                mReport.setTextColor(getResources().getColor(R.color.black));
                mReport.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_the_report, 0, 0);
                mMy.setTextColor(getResources().getColor(R.color.black));
                mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_my, 0, 0);
                break;
            //任务
            case R.id.jl_task:
                mHome.setTextColor(getResources().getColor(R.color.black));
                mHome.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_home_page, 0, 0);
                mSinge.setTextColor(getResources().getColor(R.color.black));
                mSinge.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_grab_a_single, 0, 0);
                mTask.setTextColor(getResources().getColor(R.color.bg_title));
                mTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.j_task, 0, 0);
                mReport.setTextColor(getResources().getColor(R.color.black));
                mReport.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_the_report, 0, 0);
                mMy.setTextColor(getResources().getColor(R.color.black));
                mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_my, 0, 0);
                break;
            case R.id.jl_report:
                mHome.setTextColor(getResources().getColor(R.color.black));
                mHome.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_home_page, 0, 0);
                mSinge.setTextColor(getResources().getColor(R.color.black));
                mSinge.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_grab_a_single, 0, 0);
                mTask.setTextColor(getResources().getColor(R.color.black));
                mTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_task, 0, 0);
                mReport.setTextColor(getResources().getColor(R.color.bg_title));
                mReport.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.j_the_report, 0, 0);
                mMy.setTextColor(getResources().getColor(R.color.black));
                mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_my, 0, 0);
                break;
            case R.id.jl_my:
                mHome.setTextColor(getResources().getColor(R.color.black));
                mHome.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_home_page, 0, 0);
                mSinge.setTextColor(getResources().getColor(R.color.black));
                mSinge.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_grab_a_single, 0, 0);
                mTask.setTextColor(getResources().getColor(R.color.black));
                mTask.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_task, 0, 0);
                mReport.setTextColor(getResources().getColor(R.color.black));
                mReport.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.h_the_report, 0, 0);
                mMy.setTextColor(getResources().getColor(R.color.bg_title));
                mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.j_my, 0, 0);
                break;
        }
    }






    @Override
    public void onPageSelected(int checkId) {
        switch (checkId) {
            case 0:
                setTxtColor(mHome);
                break;
            case 1:
                setTxtColor(mSinge);
                break;
            case 2:
                setTxtColor(mTask);
                break;
            case 3:
                setTxtColor(mReport);
                break;
            case 4:
                setTxtColor(mMy);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }



    //监听
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.jl_home:
                setTxtColor(mHome);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.jl_singe:
                setTxtColor(mSinge);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.jl_task:
                setTxtColor(mTask);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.jl_report:
                setTxtColor(mReport);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.jl_my:
                setTxtColor(mMy);
                mViewPager.setCurrentItem(2);
                break;
        }

    }

}

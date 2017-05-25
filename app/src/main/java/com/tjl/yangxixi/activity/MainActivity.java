package com.tjl.yangxixi.activity;

import android.os.Bundle;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.LoginBean;
import com.tjl.yangxixi.fragment.GWHomeFragment;
import com.tjl.yangxixi.fragment.HomeFragment;
import com.tjl.yangxixi.fragment.JlMyFragment;
import com.tjl.yangxixi.fragment.JlTrailReportFragment;
import com.tjl.yangxixi.fragment.MissionManagerFragment;
import com.tjl.yangxixi.fragment.MissionTaskFragment;
import com.tjl.yangxixi.fragment.ReportFragment;
import com.tjl.yangxixi.fragment.SalesmanMyFragment;
import com.tjl.yangxixi.fragment.SingeFragment;
import com.tjl.yangxixi.view.FragmentTabHost;

public class MainActivity extends BaseActivity {
	private FragmentTabHost host;
	public static MainActivity instance = null;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		instance = this;
		host=(FragmentTabHost) findViewById(R.id.tabHost);
		host.setTextSelectedColor(getResources().getColor(R.color.bg_bottom_click));
		host.setTextsColor(getResources().getColor(R.color.bg_bottom_default));
		host.setTextsSize(13);

		LoginBean.DataBean DataBean = (LoginBean.DataBean) getIntent().getExtras().get("dataBean");
		if(DataBean.getManager().equals("1")){
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_home)).setIndicator(R.drawable.h_home_page).setIndicatorSelected(R.drawable.j_home_page),new HomeFragment(host));//销售经理首页
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_singe)).setIndicator(R.drawable.h_grab_a_single).setIndicatorSelected(R.drawable.j_grab_a_single), new SingeFragment());//销售经理抢单界面
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_mission)).setIndicator(R.drawable.h_task).setIndicatorSelected(R.drawable.j_task), new MissionManagerFragment());//新版-销售经理订单列表
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_clue)).setIndicator(R.drawable.h_the_report).setIndicatorSelected(R.drawable.j_the_report), new JlTrailReportFragment());//新版-销售经理的线索报告
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_fieldpersonnel)).setIndicator(R.drawable.h_my).setIndicatorSelected(R.drawable.j_my), new JlMyFragment());//新版-销售经理的考勤(我的)
		}else{
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_home)).setIndicator(R.drawable.h_home_page).setIndicatorSelected(R.drawable.j_home_page),new GWHomeFragment(host));//销售员的首页
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_mission)).setIndicator(R.drawable.h_task).setIndicatorSelected(R.drawable.j_task), new MissionTaskFragment());//新版-销售员的订单列表
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_clue)).setIndicator(R.drawable.h_the_report).setIndicatorSelected(R.drawable.j_the_report), new ReportFragment());//销售员的线索报告
			host.addTab(host.newTabSpec().setIndicator(getString(R.string.txt_fieldpersonnel)).setIndicator(R.drawable.h_my).setIndicatorSelected(R.drawable.j_my), new SalesmanMyFragment());//新版-销售员的考勤(我的)
		}
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		try {
			if(getIntent().getBooleanExtra("status", true)==false){
				host.setPostion(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onBackPressed() {
		exit();
	}
}

package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.fragment.waiqin.HavePassedGwFragment;
import com.tjl.yangxixi.fragment.waiqin.NoHavePassedGwFragment;
import com.tjl.yangxixi.fragment.waiqin.NoProcessedGwFragment;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.view.SimpleViewPagerIndicator;

public class ApplyGoOutGwActivity extends BaseActivity implements OnClickListener{
	private List<Fragment> mTabContents = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
	private String[] mTitles = new String[] {"未处理", "未通过","已通过"};
	private SimpleViewPagerIndicator mIndicator;
	private LinearLayout lay_field_time;
	private TextView tv_field_time;
	private Dialog dialog;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	private NoProcessedGwFragment noProcessedFragment;
	private NoHavePassedGwFragment processedFragment;
	private HavePassedGwFragment havePassedFragment;
	private HttpManager http;
	private boolean isPrepared;

	private void initEvents() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

			}
			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {
				//mIndicator.scroll(position, positionOffset);
			}
			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	private void initDatas() {
		mIndicator.setTitles(mTitles);
		noProcessedFragment = new NoProcessedGwFragment();
		processedFragment = new NoHavePassedGwFragment();
		havePassedFragment = new HavePassedGwFragment();
		mTabContents.add(noProcessedFragment);
		mTabContents.add(processedFragment);
		mTabContents.add(havePassedFragment);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mTabContents.size();
			}

			@Override
			public Fragment getItem(int position) {

				return mTabContents.get(position);

			}

		};
		mViewPager.setOffscreenPageLimit(3);
		mIndicator.highLightTextView(0);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mIndicator.setItemClickEvent();

	}

	private void initViews() {
		mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
		mIndicator.setViewPager(mViewPager,0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			default:
				break;
		}
	}
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.fragment_field_personnel);
		setTitleView(true,false, this.getString(R.string.txt_fieldpersonnel_manage));
		lay_field_time = (LinearLayout) findViewById(R.id.lay_field_time);
		lay_field_time.setVisibility(View.GONE);
		http=new HttpManager(this);
	}
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		initViews();
		initDatas();
		initEvents();
	}
	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}
}

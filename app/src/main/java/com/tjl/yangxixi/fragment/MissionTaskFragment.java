package com.tjl.yangxixi.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyp.yangxixi.missionfragment.AllMissionFragments;
import com.lyp.yangxixi.missionfragment.NoYuYueFragment;
import com.lyp.yangxixi.missionfragment.ToDayMissionFragment;
import com.lyp.yangxixi.missionfragment.YesYuYueFragment;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.OrderListActivity;

/**
 *
 * @author Administrator
 *   新版-销售员的订单详情
 */
public class MissionTaskFragment extends OriginalFragment implements OnClickListener {


	View v;
	private List<Fragment> fragments;//fragment的集合
	private AllMissionFragments mAllMissionFragment;//所有任务
	private ToDayMissionFragment toDayMissionFragment;//今日任务
	private YesYuYueFragment mYesYuYueFragment;//已预约
	private NoYuYueFragment mNoYuYueFragment;//未预约
	private TextView mAllmission,mNoallot,mThenorder,mNoorder;
	private ViewPager viewPager;
	private FragmentPagerAdapter mAdapter;
	private ImageView mMybutton;


	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v= inflater.inflate(R.layout.fragment_mission_xsy, null,true);
		init();
		viewPager.setCurrentItem(0);
		return v;
	}


	private void init(){
		fragments = new ArrayList<Fragment>();
		mAllMissionFragment = new AllMissionFragments();
		toDayMissionFragment = new ToDayMissionFragment();
		mYesYuYueFragment = new YesYuYueFragment();
		mNoYuYueFragment = new NoYuYueFragment();
		fragments.add(mAllMissionFragment);
		fragments.add(toDayMissionFragment);
		fragments.add(mYesYuYueFragment);
		fragments.add(mNoYuYueFragment);
		mAllmission = (TextView) v.findViewById(R.id.tv_myallmission);
		mNoallot = (TextView) v.findViewById(R.id.tv_mytoday);
		mThenorder = (TextView) v.findViewById(R.id.tv_myyes);
		mNoorder = (TextView) v.findViewById(R.id.tv_myno);
		viewPager=(ViewPager) v.findViewById(R.id.myviewpager);
		mMybutton = (ImageView) v.findViewById(R.id.iv_mybutton);
		mAllmission.setOnClickListener(this);
		mNoallot.setOnClickListener(this);
		mThenorder.setOnClickListener(this);
		mNoorder.setOnClickListener(this);
		mMybutton.setOnClickListener(this);

		mAdapter =new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return fragments.get(arg0);
			}
		};

		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				switch (position) {
					case 0:
						mAllmission.setBackgroundResource(R.drawable.shape_title);
						mNoallot.setBackgroundResource(R.drawable.shape_title_kongbai);
						mThenorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						break;
					case 1:
						mAllmission.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoallot.setBackgroundResource(R.drawable.shape_title);
						mThenorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						break;

					case 2:
						mAllmission.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoallot.setBackgroundResource(R.drawable.shape_title_kongbai);
						mThenorder.setBackgroundResource(R.drawable.shape_title);
						mNoorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						break;

					case 3:
						mAllmission.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoallot.setBackgroundResource(R.drawable.shape_title_kongbai);
						mThenorder.setBackgroundResource(R.drawable.shape_title_kongbai);
						mNoorder.setBackgroundResource(R.drawable.shape_title);
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
		});

	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.tv_myallmission:
				viewPager.setCurrentItem(0);
				break;
			case R.id.tv_mytoday:
				viewPager.setCurrentItem(1);
				break;
			case R.id.tv_myyes:
				viewPager.setCurrentItem(2);
				break;
			case R.id.tv_myno:
				viewPager.setCurrentItem(3);
				break;
			case R.id.iv_mybutton:
				Intent orderquery = new Intent(getActivity(),OrderListActivity.class);
				startActivity(orderquery);
				break;
		}
	}


	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

}

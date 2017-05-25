package com.tjl.yangxixi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.lyp.jsonbean.LoginBean;
import com.tjl.yangxixi.view.FragmentTabHost;

/**
 *
 * @author Administrator
 *    销售员主界面
 */

public class GWHomeFragment extends OriginalFragment implements OnClickListener{

	private FragmentTabHost host;
	View v;
	private LinearLayout mTake,mReport,mMy;

	public static HomeFragment newInstance(LoginBean.DataBean dataBean) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putSerializable("dataBean", dataBean);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_homegw, null);
		init();
		return v;
	}
	public GWHomeFragment() {
		// TODO Auto-generated constructor stub
	}
	public GWHomeFragment(FragmentTabHost host) {
		// TODO Auto-generated constructor stub
		this.host=host;

	}

	private void init(){
		mTake=(LinearLayout) v.findViewById(R.id.gwhome_task);
		mReport=(LinearLayout) v.findViewById(R.id.gwhome_report);
		mMy=(LinearLayout) v.findViewById(R.id.gwhome_my);
		mTake.setOnClickListener(this);
		mReport.setOnClickListener(this);
		mMy.setOnClickListener(this);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.gwhome_task:
				host.setPostion(1);
				break;
			case R.id.gwhome_report:
				host.setPostion(2);
				break;
			case R.id.gwhome_my:
				host.setPostion(3);
				break;
		}
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
	}

//	//跳转到相对应的Fragment
//	private void setFragment(Fragment fragment){
//		fm = getFragmentManager();
//		ft = fm.beginTransaction();
//		ft.add(R.id.gwfragment, fragment);
//		ft.commit();
//	}
}

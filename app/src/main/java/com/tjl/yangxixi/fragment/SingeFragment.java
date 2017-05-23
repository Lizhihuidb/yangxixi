package com.tjl.yangxixi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lyp.percent.PercentRelativeLayout;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.SingeDetailsActivity;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.view.FragmentTabHost;

/**
 *
 * @author Administrator
 *	销售经理的抢单界面
 */
public class SingeFragment extends OriginalFragment implements OnClickListener{

	private FragmentTabHost host;
	View v;
	private UserInfoBean user;
	private PercentRelativeLayout percent_a;
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_singe_item, null);
		init();
		return v;
	}

	public SingeFragment() {
		// TODO Auto-generated constructor stub
	}
	public SingeFragment(FragmentTabHost host) {
		// TODO Auto-generated constructor stub
		this.host=host;

	}


	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	private void init(){
		percent_a = (PercentRelativeLayout) v.findViewById(R.id.percent_detauls);
		percent_a.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			case R.id.percent_detauls:
				intent = new Intent(getActivity(),SingeDetailsActivity.class);
				startActivity(intent);

				break;

		}
	}

}

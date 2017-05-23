package com.tjl.yangxixi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.PersonageMessageActivity;
import com.tjl.yangxixi.activity.PersonageMyMessageActivity;
import com.tjl.yangxixi.activity.ProceduresActivity;

/**
 *
 * @author Administrator
 *   销售员我的
 */
public class SalesmanMyFragment extends OriginalFragment implements OnClickListener{


	View v;
	private RelativeLayout mServiceManual,mUseClauses,mFeedback,mAbout,mVersion;
	private LinearLayout mMessage;
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v=inflater.inflate(R.layout.fragment_salesmanmy, null);
		init();
		return v;
	}



	private void init(){
		mMessage = (LinearLayout) v.findViewById(R.id.ll_jlmy_message);
		mServiceManual = (RelativeLayout) v.findViewById(R.id.rl_jlmy_service_manual);
		mUseClauses = (RelativeLayout) v.findViewById(R.id.rl_jlmy_use_clauses);
		mFeedback = (RelativeLayout) v.findViewById(R.id.rl_jlmy_feedback);
		mAbout = (RelativeLayout) v.findViewById(R.id.rl_jlmy_about);
		mVersion = (RelativeLayout) v.findViewById(R.id.rl_jlmy_version);
		mMessage.setOnClickListener(this);
		mServiceManual.setOnClickListener(this);
		mUseClauses.setOnClickListener(this);
		mFeedback.setOnClickListener(this);
		mAbout.setOnClickListener(this);
		mVersion.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			case R.id.ll_jlmy_message:
				Toast.makeText(getActivity(), "个人中心", 0).show();
				intent = new Intent(getActivity(),PersonageMyMessageActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_jlmy_service_manual:
				Toast.makeText(getActivity(), "使用手册", 0).show();
				intent = new Intent(getActivity(),ProceduresActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_jlmy_use_clauses:
				Toast.makeText(getActivity(), "使用条款和隐私政策", 0).show();
				break;
			case R.id.rl_jlmy_feedback:
				Toast.makeText(getActivity(), "意见反馈", 0).show();
				break;
			case R.id.rl_jlmy_about:
				Toast.makeText(getActivity(), "关于我们", 0).show();
				break;
			case R.id.rl_jlmy_version:
				Toast.makeText(getActivity(), "当前版本", 0).show();
				break;
		}
	}

}

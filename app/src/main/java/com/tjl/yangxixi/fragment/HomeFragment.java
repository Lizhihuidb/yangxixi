package com.tjl.yangxixi.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.MessageNumberBean;
import com.tjl.yangxixi.bean.MessageNumberGwBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.view.FragmentTabHost;

/**
 *
 * @author Administrator
 *    销售经理主界面
 */

public class HomeFragment extends OriginalFragment implements OnClickListener{
	private FragmentTabHost host;
	//	private LinearLayout lay_jrrw,lay_xsbg,lay_wqgl,lay_czsc,lay_gyyds,lay_zhxx;
//	private ImageView icon_drop_jrrw,icon_drop_wqgl;
//	private RelativeLayout rel_jrrw,rel_wqgl;
//	private TextView tv_drop_jrrw,tv_drop_wqgl;
	private HttpManager http;
	private UserInfoBean user;
	private List<MessageNumberBean> mList;
	private List<MessageNumberGwBean> gwList;

	private LinearLayout mSingle,mTake,mReport,mMy;

	View v;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_homes, null);
//		init(v);
		init();
		return v;
	}
	public HomeFragment() {
		// TODO Auto-generated constructor stub
	}
	public HomeFragment(FragmentTabHost host) {
		// TODO Auto-generated constructor stub
		this.host=host;

	}

	private void init(){
		mSingle=(LinearLayout) v.findViewById(R.id.home_single);
		mTake=(LinearLayout) v.findViewById(R.id.home_task);
		mReport=(LinearLayout) v.findViewById(R.id.home_report);
		mMy=(LinearLayout) v.findViewById(R.id.home_my);
		mSingle.setOnClickListener(this);
		mTake.setOnClickListener(this);
		mReport.setOnClickListener(this);
		mMy.setOnClickListener(this);
	}


//	private void init(View v){
//		new ToolsUtils().initTitleView(false,false, MyApplication.getInstance().getUserList().get(0).getStore()+"-"+MyApplication.getInstance().getUserList().get(0).getEmp_name(), getActivity(),v);
//		user=MyApplication.getInstance().getUserList().get(0);
//		
//		http=new HttpManager(getActivity());
//		http.setHttpCallBackListener(listener);
//		if(user.getPosition().equals("JL")){
//			mList=new ArrayList<MessageNumberBean>();
//			http.doPost(ConstantsUrl.getMessagerNumber(user.getEmp_id()), 1, false);
//		}else{
//			gwList=new ArrayList<MessageNumberGwBean>();
//			http.doPost(ConstantsUrl.getMessagerNumberGw(user.getEmp_id()), 2, false);
//		}
//		lay_jrrw = (LinearLayout) v.findViewById(R.id.lay_jrrw);
//		lay_xsbg = (LinearLayout) v.findViewById(R.id.lay_xsbg);
//		lay_wqgl = (LinearLayout) v.findViewById(R.id.lay_wqgl);
//		lay_czsc = (LinearLayout) v.findViewById(R.id.lay_czsc);
//		lay_gyyds = (LinearLayout) v.findViewById(R.id.lay_gyyds);
//		lay_zhxx = (LinearLayout) v.findViewById(R.id.lay_zhxx);
//		
//		icon_drop_jrrw=(ImageView) v.findViewById(R.id.icon_drop_jrrw);
//		icon_drop_wqgl=(ImageView) v.findViewById(R.id.icon_drop_wqgl);
//		rel_jrrw=(RelativeLayout) v.findViewById(R.id.rel_jrrw);
//		rel_wqgl=(RelativeLayout) v.findViewById(R.id.rel_wqgl);
//		tv_drop_jrrw=(TextView) v.findViewById(R.id.tv_drop_jrrw);
//		tv_drop_wqgl=(TextView) v.findViewById(R.id.tv_drop_wqgl);
//		
//		lay_jrrw.setOnClickListener(this);
//		lay_xsbg.setOnClickListener(this);
//		lay_wqgl.setOnClickListener(this);
//		lay_czsc.setOnClickListener(this);
//		lay_gyyds.setOnClickListener(this);
//		lay_zhxx.setOnClickListener(this);
//		host.setOnItemSelectListener(new OnItemSelectListener() {
//			
//			@Override
//			public void onItemSelect(int p, TabSpec tab, Fragment fragment) {
//				// TODO Auto-generated method stub
//				if(p==1){
//					rel_jrrw.setVisibility(View.GONE);
//				}else if(p==3){
//					rel_wqgl.setVisibility(View.GONE);
//				}
//			}
//		});
//	}
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch (v.getId()) {
//		case R.id.lay_jrrw:
//			host.setPostion(1);
//			break;
//
//		case R.id.lay_xsbg:
//			host.setPostion(2);
//			break;
//		case R.id.lay_wqgl:
//			host.setPostion(3);
//			break;
//		case R.id.lay_czsc:
//			break;
//		case R.id.lay_gyyds:
//			Intent intent =new Intent(getActivity(),AboutUsActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.lay_zhxx:
//			Intent userInfoIntent =new Intent(getActivity(),UserInfoActivity.class);
//			startActivity(userInfoIntent);
//			break;
//			
//		}
//	}
//	private void initDate(){
//		int number=mList.get(0).getNfCunt()+mList.get(0).getBtCunt();
//		if(number>0){
//			rel_jrrw.setVisibility(View.VISIBLE);
//			tv_drop_jrrw.setText(number+"");
//		}
//		if(mList.get(0).getApCunt()>0){
//			rel_wqgl.setVisibility(View.VISIBLE);
//			tv_drop_wqgl.setText(mList.get(0).getApCunt()+"");
//		}
//		SharePreferenceManager.saveIntPreference(getActivity(), "libraryNumber", mList.get(0).getBtCunt());
//	}
//	private void initDateGw(){
//		if(gwList.get(0).getTsCunt()>0){
//			rel_jrrw.setVisibility(View.VISIBLE);
//			tv_drop_jrrw.setText(gwList.get(0).getTsCunt()+"");
//		}
//		if(gwList.get(0).getApCunt()>0){
//			rel_wqgl.setVisibility(View.VISIBLE);
//			tv_drop_wqgl.setText(gwList.get(0).getApCunt()+"");
//		}
//	}
//	HttpCallBackListener listener = new HttpCallBackListener() {
//		
//		@Override
//		public void onSuccess(HttpMessage result, int tag, Object obj) {
//			// TODO Auto-generated method stub
//			switch (tag) {
//			case 1:
//				mList=JSON.parseArray(result.getData(), MessageNumberBean.class);
//				initDate();
//				break;
//
//			case 2:
//				gwList=JSON.parseArray(result.getData(), MessageNumberGwBean.class);
//				initDateGw();
//				break;
//			}
//		}
//		
//		@Override
//		public void onError(String msg, int tag, Object obj) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
//	@Override
//	protected void lazyLoad() {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.home_single:
				host.setPostion(1);
				break;
			case R.id.home_task:
				host.setPostion(2);
				break;
			case R.id.home_report:
				host.setPostion(3);
				break;
			case R.id.home_my:
				host.setPostion(4);
				break;

		}



	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

}

package com.tjl.yangxixi.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.utils.Constants;
import com.tjl.yangxixi.utils.PxDpUtils;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *
 * host=(FragmentTabHost) findViewById(R.id.tabHost);
 * host.setTextSelectedColor(Color.RED);
 * host.addTab(host.newTabSpec().setIndicator(getString(R.string.tab_category)), new TabRecommendFragment());
 * host.addTab(host.newTabSpec().setIndicator(getString(R.string.tab_software)), new TabSoftwareFragment());
 * host.addTab(host.newTabSpec().setIndicator(getString(R.string.tab_game)), new TabGameFragment());
 * host.addTab(host.newTabSpec().setIndicator(getString(R.string.tab_manage)), new TabManageFragment());
 * @author GT
 *
 */
public class FragmentTabHost extends RelativeLayout{
	private HashMap<TabSpec,Fragment> mapCls;
	private List<TabSpec> listTabSpec;
	private LinearLayout tabLayout,tabLayoutParent;
	private FrameLayout contentLayout;
	private FragmentManager manager;
	private boolean isFristAddTab=true;
	private OnItemSelectListener listener;

	//private LinearLayout.LayoutParams tabItemLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
	private LinearLayout.LayoutParams tabItemLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, PxDpUtils.dip2px(getContext(), Constants.ACTIONBAR_ICON_HEIGHT), 1);
	private LayoutParams tabItemChildLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	
	private int contentId=R.id.fragmentManagerContentLayout;
	
	private View lineTop,lineBottom;
	
	private int postion=0;

	private Fragment lastFragment;
	
	private int textSelectedColor=Color.BLACK;
	private int textsColor=Color.BLACK;
	private int textsSize=0;

	
	private void init(Context context) {
		contentId=hashCode();
		listTabSpec=new ArrayList<TabSpec>();
		mapCls=new HashMap<TabSpec, Fragment>();
		lineTop=new View(getContext());
		lineBottom=new View(getContext());
		LinearLayout.LayoutParams lineLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1,0);
		lineTop.setBackgroundColor(Color.GRAY);
		lineBottom.setBackgroundColor(Color.GRAY);
		lineTop.setLayoutParams(lineLp);
		lineBottom.setLayoutParams(lineLp);
		lineTop.setVisibility(View.GONE);
		lineBottom.setVisibility(View.GONE);
		
		tabLayoutParent=new LinearLayout(getContext());
		tabLayout=new LinearLayout(getContext());
		tabLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,PxDpUtils.dip2px(context, Constants.ACTIONBAR_TAB_HEIGHT),1));
		tabLayoutParent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,PxDpUtils.dip2px(context, Constants.ACTIONBAR_TAB_HEIGHT)));
		tabLayoutParent.setBackgroundColor(getResources().getColor(R.color.bg_bottom));
		tabLayoutParent.setOrientation(LinearLayout.VERTICAL);
		
		tabLayoutParent.addView(lineTop);
		tabLayoutParent.addView(tabLayout);
		tabLayoutParent.addView(lineBottom);
		
		contentLayout=new FrameLayout(getContext());
		contentLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		contentLayout.setId(contentId);
		addView(contentLayout);
		addView(tabLayoutParent);
		manager=((FragmentActivity)context).getSupportFragmentManager();
		tabBringToBottom();
	}
	
	/**
	 * ����tab�ĸ߶�
	 * @param height
	 */
	public void setTabHeight(int height){
		tabLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height,1));
		tabLayoutParent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,height));
	}
	
	public void setCut(boolean flag) {
		setCutTop(flag);
		setCutBootom(flag);
	}
	public void setCutTop(boolean flag) {
		lineTop.setVisibility(View.VISIBLE);
	}
	public void setCutBootom(boolean flag) {
		lineBottom.setVisibility(View.VISIBLE);
	}
	public void tabBringToTop() {
		contentLayout.setPadding(0, PxDpUtils.dip2px(getContext(), Constants.ACTIONBAR_TAB_HEIGHT), 0, 0);
		LayoutParams lp = (LayoutParams) tabLayoutParent.getLayoutParams();
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
	}
	public void tabBringToBottom() {
		contentLayout.setPadding(0,  0, 0,PxDpUtils.dip2px(getContext(), Constants.ACTIONBAR_TAB_HEIGHT));
		LayoutParams lp = (LayoutParams) tabLayoutParent.getLayoutParams();
		lp.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
	}
	
	public FragmentTabHost(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public FragmentTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public FragmentTabHost(Context context) {
		super(context);
		init(context);
	}
	public void addTab(TabSpec tab,Fragment fragment){
		if(tab==null) {
			//Log.e(Constants.LOG_NAME, getContext().getString(R.string.exception_fragmenttabhost_tabspec_null));
			return;
		}
		mapCls.put(tab, fragment);
		View v = tab.getInView();
		if(v!=null){
			addTabView(v,tab);
		}else if(tab.getInString()!=null&&tab.getIconResId()!=0){
			addTabView(getIconStringTabView(tab.getInString(), tab.getIconResId()),tab);
		}else if(tab.getInString()!=null){
			addTabView(getIconStringTabView(tab.getInString(), tab.getIconResId()),tab);
		}else if(tab.getIconResId()!=0){
			addTabView(getIconTabView(tab.getIconResId()), tab);
		}else{
			//Log.e(Constants.LOG_NAME, getContext().getString(R.string.exception_fragmenttabhost_tabspec_info_null));
		}
		if(isFristAddTab){
			setPostion(0);
			isFristAddTab=false;
		}
	}
	
	public void setTabBackgroundResource(int resid){
		tabLayout.setBackgroundResource(resid);
	}
	public void setTabBackgroundColor(int color) {
		tabLayout.setBackgroundColor(color);
	}
	@Deprecated
	public void setTabBackgroundDrawable(Drawable background) {
		tabLayout.setBackgroundDrawable(background);
	}
	
	
	private View getIconTabView(int resId) {
		ImageView iv=new ImageView(getContext());
		tabItemLayoutParams.gravity=Gravity.CENTER;
		iv.setLayoutParams(tabItemLayoutParams);
		iv.setImageResource(resId);
		iv.setTag("ImageView");
		return iv;
	}
	
	private View getIconStringTabView(String str,int resId){
		LinearLayout ll=new LinearLayout(getContext());
		ll.setOrientation(LinearLayout.VERTICAL);
		if(resId!=0){
			ll.addView(getIconTabView(resId));
		}
		TextView tv=new TextView(getContext());
		tv.setTag("TextView");
		if(textsColor!=0){
			tv.setTextColor(textsColor);
		}
		if(textsSize!=0)
			tv.setTextSize(textsSize);
		tv.setGravity(Gravity.CENTER);
		tv.setText(str);
		ll.addView(tv);
		ll.setGravity(Gravity.CENTER);
		ll.setLayoutParams(tabItemLayoutParams);
		return ll;
	}
	
	public void setTextsSize(int size){
		if(size==0){
			return;
		}
		textsSize=size;
		for (int i = 0; i < tabLayout.getChildCount(); i++) {
			View view = tabLayout.getChildAt(i);
			setTextViewSize(view.findViewWithTag("TextView"), size);
		}
	}
	
	public void setTextsColor(int color){
		textsColor=color;
		for (int i = 0; i < tabLayout.getChildCount(); i++) {
			if(i!=postion){
				View view = tabLayout.getChildAt(i);
				setTextViewColor(view.findViewWithTag("TextView"), color);
			}
		}
	}
	public void setTextSelectedColor(int color){
		textSelectedColor=color;
		for (int i = 0; i < tabLayout.getChildCount(); i++) {
			if(i==postion){
				View view = tabLayout.getChildAt(i);
				setTextViewColor(view.findViewWithTag("TextView"), color);
			}
		}
	}
	
	private void addTabView(View view,TabSpec tab){
		RelativeLayout ll=new RelativeLayout(getContext());
		view.setLayoutParams(tabItemChildLayoutParams);
		ll.addView(view);
		ll.setLayoutParams(tabItemLayoutParams);
		ll.setGravity(Gravity.CENTER);
		ll.setTag(tab);
		listTabSpec.add(tab);
		ll.setOnClickListener(tabOnclick);
		tabLayout.addView(ll);
	}
	
	private OnClickListener tabOnclick=new OnClickListener() {
		@Override
		public void onClick(View v) {
			setPostion(listTabSpec.indexOf(v.getTag()));
		}
	};
	private void showFragment(Fragment fg) {
		if(lastFragment!=null){
			manager.beginTransaction().hide(lastFragment).commitAllowingStateLoss();
		}
		lastFragment=fg;
		if(fg.isAdded()){
			manager.beginTransaction().show(fg).commitAllowingStateLoss();
		}else{
			manager.beginTransaction().add(contentId, fg).commitAllowingStateLoss();
		}
	}
	
	
	private void setTextViewSize(Object tv ,int size){
		if(tv!=null&&size!=0&&tv instanceof TextView){
			((TextView) tv).setTextSize(size);
		}
	}
	private void setTextViewColor(Object tv ,int color){
		if(tv!=null&&color!=0&&tv instanceof TextView){
			((TextView) tv).setTextColor(color);
		}
	}
	private void setImageViewRes(Object iv,int resId){
		if(iv!=null&&resId!=0&&iv instanceof ImageView){
			((ImageView)iv).setImageResource(resId);
		}
	}
	public void setPostion(int p) {
		View itemView=tabLayout.getChildAt(p);
		if(itemView==null)return;
		TabSpec tab = (TabSpec) itemView.getTag();
		Fragment fg = mapCls.get(tab);
		showFragment(fg);
		this.postion=p;
		for (int i = 0; i < tabLayout.getChildCount(); i++) {
			View view= tabLayout.getChildAt(i);
			setTextViewColor(view.findViewWithTag("TextView"), textsColor);
			TabSpec tab2 = (TabSpec) view.getTag();
			setImageViewRes(view.findViewWithTag("ImageView"), tab2.getIconResId());
		}
		
		setTextViewColor(itemView.findViewWithTag("TextView"), textSelectedColor);
		setImageViewRes(itemView.findViewWithTag("ImageView"), tab.getIconSelectedResid());
		if(listener!=null){
			listener.onItemSelect(p, tab, fg);
		}
	}
	
	public TabSpec newTabSpec() {
		return new TabSpec();
	}
	
	
	public class TabSpec{
		private String inString;
		private View inView;
		private int iconResId;
		private int iconSelectedResid;
		
		public TabSpec setIndicator(String str) {
			inString=str;
			return this;
		}
		public TabSpec setIndicator(View v) {
			inView=v;
			return this;
		}
		public TabSpec setIndicator(int resId) {
			iconResId=resId;
			return this;
		}
		public TabSpec setIndicatorSelected(int resId) {
			iconSelectedResid=resId;
			return this;
		}
		
		public int getIconSelectedResid() {
			return iconSelectedResid;
		}
		public String getInString() {
			return inString;
		}
		public View getInView() {
			return inView;
		}
		public int getIconResId() {
			return iconResId;
		}
	}
	public int getPostion() {
		return postion;
	}
	public void setOnItemSelectListener(OnItemSelectListener listener) {
		this.listener=listener;
	}
	
	public interface OnItemSelectListener{
		public void onItemSelect(int p, TabSpec tab, Fragment fragment);
	}
}

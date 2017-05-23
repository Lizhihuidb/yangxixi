package com.tjl.yangxixi.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.fragment.waiqin.HavePassedFragment;
import com.tjl.yangxixi.fragment.waiqin.NoHavePassedFragment;
import com.tjl.yangxixi.fragment.waiqin.NoProcessedFragment;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.PxDpUtils;
import com.tjl.yangxixi.utils.ToolsUtils;
import com.tjl.yangxixi.view.SimpleViewPagerIndicator;
import com.tjl.yangxixi.wheelview.NumericWheelAdapter;
import com.tjl.yangxixi.wheelview.OnWheelChangedListener;
import com.tjl.yangxixi.wheelview.WheelView;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 *
 * @author Administrator
 *    销售经理端的外勤
 */
public class FieldPersonnelFragment extends OriginalFragment implements OnClickListener{
	private List<Fragment> mTabContents = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
	private String[] mTitles = new String[] {"未处理", "未通过","已通过"};
	private SimpleViewPagerIndicator mIndicator;
	private LinearLayout lay_field_time;
	private TextView tv_field_time;
	private Dialog dialog;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	private NoProcessedFragment noProcessedFragment;
	private NoHavePassedFragment processedFragment;
	private HavePassedFragment havePassedFragment;
	private HttpManager http;
	private boolean isPrepared;
	private PopupWindow popupWindow;
	private LinearLayout ly_item1,ly_item2;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_field_personnel, null);
		init(v);
		isPrepared = true;
		lazyLoad();
		initViews(v);
		initDatas();
		initEvents();

		return v;
	}
	private void init(View v){
		http=new HttpManager(getActivity());
		new ToolsUtils().initTitleView(false,false, getActivity().getString(R.string.txt_fieldpersonnel_manage), getActivity(),v);
		lay_field_time = (LinearLayout) v.findViewById(R.id.lay_field_time);
		tv_field_time = (TextView) v.findViewById(R.id.tv_field_time);

		//tv_field_time.setText(ToolsUtils.getNowDate());
		lay_field_time.setOnClickListener(this);
	}
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
		String dateTime=tv_field_time.getText().toString();
		mIndicator.setTitles(mTitles);
		noProcessedFragment = new NoProcessedFragment(dateTime,handler);
		processedFragment = new NoHavePassedFragment(dateTime);
		havePassedFragment = new HavePassedFragment(dateTime);
		mTabContents.add(noProcessedFragment);
		mTabContents.add(processedFragment);
		mTabContents.add(havePassedFragment);
		mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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

	private void initViews(View v) {
		mIndicator = (SimpleViewPagerIndicator) v.findViewById(R.id.id_stickynavlayout_indicator);
		mViewPager = (ViewPager) v.findViewById(R.id.id_stickynavlayout_viewpager);
		mIndicator.setViewPager(mViewPager,0);
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					if(tv_field_time.getText().equals("全部申请")){
						refreshSubPage("");
					}else{
						refreshSubPage(tv_field_time.getText().toString());
					}
					break;

				default:
					break;
			}
		};
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.lay_field_time:
				initPopuptWindow();
				//showDateTimePicker();
				break;

			default:
				break;
		}
	}
	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	private void showDateTimePicker() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//dialog.setTitle("请选择日期与时间");
		// 找到dialog的布局文件
		//LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.time_field_layout, null);

		// 年
		final WheelView wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		final WheelView wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		final WheelView wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		wv_day.setCurrentItem(day - 1);


		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String
						.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小
		int textSize = 0;

		textSize = PxDpUtils.sp2px(getActivity(), 18);
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view
				.findViewById(R.id.btn_datetime_cancel);
		final EditText editContent = (EditText) view.findViewById(R.id.editContent);
		// 确定
		btn_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 如果是个数,则显示为"02"的样式
				String parten = "00";
				DecimalFormat decimal = new DecimalFormat(parten);
				// 设置日期的显示
				// tv_time.setText((wv_year.getCurrentItem() + START_YEAR) + "-"
				// + decimal.format((wv_month.getCurrentItem() + 1)) + "-"
				// + decimal.format((wv_day.getCurrentItem() + 1)) + " "
				// + decimal.format(wv_hours.getCurrentItem()) + ":"
				// + decimal.format(wv_mins.getCurrentItem()));
				String fkTime=(wv_year.getCurrentItem() + START_YEAR) + "-"
						+ decimal.format((wv_month.getCurrentItem() + 1)) + "-"
						+ decimal.format((wv_day.getCurrentItem() + 1));
				tv_field_time.setText(fkTime);
/*				try {
					//http.doPost(ConstantsUrl.requestBookCall(cludeId,URLEncoder.encode(fkTime,"UTF-8"),URLEncoder.encode(editContent.getText().toString(),"UTF-8")), 3, false);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}*/
				refreshSubPage(fkTime);
				dialog.dismiss();
			}
		});
		// 取消
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		// 设置dialog的布局,并显示
		dialog.setContentView(view);
		dialog.show();
	}
	public void refreshSubPage(String fkTime){
		http.showProgressDialog();
		noProcessedFragment.refresh(fkTime);
		processedFragment.refresh(fkTime);
		havePassedFragment.refresh(fkTime);
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}
	/**
	 * 创建PopupWindow
	 */
	protected void initPopuptWindow() {
		// TODO Auto-generated method stub
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = getActivity().getLayoutInflater().inflate(R.layout.pop_wq_menu, null,
				false);
		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		ly_item1=(LinearLayout) popupWindow_view.findViewById(R.id.ly_item1);
		ly_item2=(LinearLayout) popupWindow_view.findViewById(R.id.ly_item2);

		// 设置动画效果
		//popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 点击其他地方消失
		popupWindow_view.setOnTouchListener(new OnTouchListener() {


			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}
		});
/*        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
		ColorDrawable dw = new ColorDrawable(0x00000000);
		popupWindow.setBackgroundDrawable(dw);*/
		ly_item1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_field_time.setText("全部申请");
				refreshSubPage("");
				popupWindow.dismiss();
			}
		});
		ly_item2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDateTimePicker();
				popupWindow.dismiss();

			}
		});
		popupWindow.showAsDropDown(lay_field_time);
	}
}

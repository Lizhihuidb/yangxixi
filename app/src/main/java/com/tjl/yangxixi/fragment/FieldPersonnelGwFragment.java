package com.tjl.yangxixi.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.ApplyGoOutGwActivity;
import com.tjl.yangxixi.activity.SignInApplyActivity;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.fragment.waiqin.HavePassedFragment;
import com.tjl.yangxixi.fragment.waiqin.NoHavePassedFragment;
import com.tjl.yangxixi.fragment.waiqin.NoProcessedFragment;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.PxDpUtils;
import com.tjl.yangxixi.utils.ToolsUtils;
import com.tjl.yangxixi.view.SimpleViewPagerIndicator;
import com.tjl.yangxixi.wheelview.NumericWheelAdapter;
import com.tjl.yangxixi.wheelview.OnWheelChangedListener;
import com.tjl.yangxixi.wheelview.WheelView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * @author Administrator
 *     外勤
 */
public class FieldPersonnelGwFragment extends OriginalFragment implements
		OnClickListener {
	private TextView tv_query_apply, tv_sign_in, tv_waiqin_time,
			tv_waiqin_apply, tv_waiqin_again;
	private EditText edi_cq_cause, edi_cq_addres;
	private LinearLayout lay_waiqin_time;
	private HttpManager http;
	private boolean isPrepared;
	private Dialog dialog;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	private UserInfoBean user;

	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_waiqin_gw, null);
		init(v);
		isPrepared = true;
		lazyLoad();

		return v;
	}

	private void init(View v) {
		user = MyApplication.getInstance().getUserList().get(0);
		new ToolsUtils().initTitleView(false,false, getActivity().getString(R.string.txt_fieldpersonnel_manage), getActivity(),v);
		tv_query_apply = (TextView) v.findViewById(R.id.tv_query_apply);
		tv_sign_in = (TextView) v.findViewById(R.id.tv_sign_in);
		tv_waiqin_time = (TextView) v.findViewById(R.id.tv_waiqin_time);
		tv_waiqin_apply = (TextView) v.findViewById(R.id.tv_waiqin_apply);
		tv_waiqin_again = (TextView) v.findViewById(R.id.tv_waiqin_again);
		edi_cq_cause = (EditText) v.findViewById(R.id.edi_cq_cause);
		edi_cq_addres = (EditText) v.findViewById(R.id.edi_cq_addres);
		lay_waiqin_time = (LinearLayout) v.findViewById(R.id.lay_waiqin_time);

		lay_waiqin_time.setOnClickListener(this);
		tv_query_apply.setOnClickListener(this);
		tv_sign_in.setOnClickListener(this);
		tv_waiqin_apply.setOnClickListener(this);
		tv_waiqin_again.setOnClickListener(this);

		http = new HttpManager(getActivity());
		http.setHttpCallBackListener(listener);
		// http.doPost(url, tag, isShowProgress);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.lay_waiqin_time:
				showDateTimePicker();
				break;
			case R.id.tv_waiqin_apply:
				boolean convertSuccess = true;
				// 　　　　 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					format.setLenient(false);
					format.parse(tv_waiqin_time.getText().toString());
				} catch (ParseException e) {
					// e.printStackTrace();
					// 如果throw
					// java.text.ParseException或者NullPointerException，就说明格式不对
					convertSuccess = false;
				}
				if (TextUtils.isEmpty(edi_cq_cause.getText())) {
					Toast.makeText(getActivity(), "出勤事由不能为空", Toast.LENGTH_SHORT)
							.show();
				} else if (TextUtils.isEmpty(edi_cq_addres.getText())) {
					Toast.makeText(getActivity(), "出勤地址不能为空", Toast.LENGTH_SHORT)
							.show();
				} else if (convertSuccess==false) {
					Toast.makeText(getActivity(), "请选择出勤时间", Toast.LENGTH_SHORT)
							.show();
				} else {
					try {
						http.doPost(ConstantsUrl.saleGetApplication(user.getEmp_id(),
								URLEncoder.encode(tv_waiqin_time.getText().toString(),"UTF-8"),
								URLEncoder.encode(edi_cq_addres.getText().toString(),"UTF-8"),
								URLEncoder.encode(edi_cq_cause.getText().toString(),"UTF-8")), 1, false);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case R.id.tv_waiqin_again:
				edi_cq_cause.setText("");
				edi_cq_addres.setText("");
				tv_waiqin_time.setText("请选择时间");
				break;
			case R.id.tv_query_apply:
				Intent intent = new Intent(getActivity(),ApplyGoOutGwActivity.class);
				startActivity(intent);
				break;
			case R.id.tv_sign_in:
				Intent intent1 = new Intent(getActivity(),SignInApplyActivity.class);
				startActivity(intent1);
				break;
		}
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			switch (tag) {
				case 1:
					Toast.makeText(getActivity(), result.getMsg(),
							Toast.LENGTH_SHORT).show();
					if(result.getResult()==1){
						edi_cq_cause.setText("");
						edi_cq_addres.setText("");
						tv_waiqin_time.setText("请选择时间");
					}
					break;

				default:
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub

		}
	};

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
		// dialog.setTitle("请选择日期与时间");
		// 找到dialog的布局文件
		// LayoutInflater inflater = (LayoutInflater)
		// getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.dilog_waiqin_time, null);

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

		// 时
		final WheelView wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);
		wv_hours.setCurrentItem(hour);

		// 分
		final WheelView wv_mins = (WheelView) view.findViewById(R.id.mins);
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
		wv_mins.setCyclic(true);
		wv_mins.setCurrentItem(minute);

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
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
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view
				.findViewById(R.id.btn_datetime_cancel);
		final EditText editContent = (EditText) view
				.findViewById(R.id.editContent);
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
				String fkTime = (wv_year.getCurrentItem() + START_YEAR) + "-"
						+ decimal.format((wv_month.getCurrentItem() + 1)) + "-"
						+ decimal.format((wv_day.getCurrentItem() + 1)) + " "
						+ decimal.format(wv_hours.getCurrentItem()) + ":"
						+ decimal.format(wv_mins.getCurrentItem());
				tv_waiqin_time.setText(fkTime);
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
}

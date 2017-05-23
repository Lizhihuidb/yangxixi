package com.tjl.yangxixi.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.tjl.yangxixi.R;

/**
 *
 * @author Administrator
 *	签到
 */
public class SignActivity extends Activity implements OnClickListener, LocationSource, AMapLocationListener{

	private ImageView mBack;
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private MarkerOptions markerOption;
	private TextView mArrive;
	private TextView mLeave;
	boolean flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		initview();
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	private void initview(){
		mBack = (ImageView) findViewById(R.id.iv_sign_back);
		mArrive = (TextView) findViewById(R.id.arrive);
		mLeave = (TextView) findViewById(R.id.leave);
		mapView = (MapView) findViewById(R.id.map);
		mBack.setOnClickListener(this);
		mArrive.setOnClickListener(this);
		mLeave.setOnClickListener(this);
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(this);
			//设置是否只定位一次,默认为false
			mLocationOption.setOnceLocation(true);
			//设置定位间隔,单位毫秒,默认为2000ms
			mLocationOption.setInterval(10000);
			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
			mLocationOption.setHttpTimeOut(20000);
			mlocationClient.startLocation();
		}
	}
	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 定位成功后回调函数
	 */
	public void onLocationChanged(AMapLocation amapLocation) {
		// TODO Auto-generated method stub
		if (amapLocation != null&& amapLocation.getErrorCode() == 0) {
			mListener.onLocationChanged(amapLocation);//注册定位监听
			markerOption = new MarkerOptions().anchor(0.5f, 0.5f)
					.position(new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude())).title(amapLocation.getAddress())
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker))
					.draggable(false);
			//请求签到或签退成功后再添加标记
			Marker marker=aMap.addMarker(markerOption);
			marker.showInfoWindow();//设置默认显示一个infowinfow
			String str=new SimpleDateFormat("yyyy年M月dd日 HH时mm分ss秒").format(new Date());
			Toast.makeText(this, ""+amapLocation.getAddress(),10).show();
			Log.i("test", ""+amapLocation.getAddress());;
			if (flag) {
				Toast.makeText(this, "我已到达:"+amapLocation.getAddress()+str, 0).show();
			} else {
				Toast.makeText(this, "我要离开:"+amapLocation.getAddress()+str, 0).show();
			}
		}
		else {
			String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
			Toast.makeText(this, "定位失败!请重新定位", 0).show();
			Log.e("AmapErr",errText);
		}
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.iv_sign_back:
				finish();
				break;
			case R.id.arrive:
				mlocationClient.startLocation();
				flag = true;
				break;
			case R.id.leave:
				mlocationClient.startLocation();
				flag = false;
				break;
		}
	}


}

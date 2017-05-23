package com.tjl.yangxixi.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.LogJY;

public class SignInMapActivity extends BaseActivity implements OnClickListener{
	private HttpManager http;
	private int id;
	public MapView mapView = null;
	public BaiduMap baiduMap = null;
	private TextView tv_signin;
	// 定位相关声明
	public LocationClient locationClient = null;
	// 自定义图标
	BitmapDescriptor mCurrentMarker = null;
	boolean isFirstLoc = true;// 是否首次定位
	private String latitude="",longitude="",addres="";//保存经纬度和地址

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_map);
		setTitleView(true, false,"签到申请");
		tv_signin=(TextView) findViewById(R.id.tv_signin);
		mapView = (MapView) this.findViewById(R.id.bmapView); // 获取地图控件引用
		baiduMap = mapView.getMap();
		// 开启定位图层
		baiduMap.setMyLocationEnabled(true);
		// 修改为自定义marker
		/*
		 * mCurrentMarker = BitmapDescriptorFactory
		 * .fromResource(R.drawable.icon_gcoding); baiduMap
		 * .setMyLocationConfigeration(new MyLocationConfiguration(
		 * LocationMode.NORMAL, true, mCurrentMarker));
		 */
		locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
		locationClient.registerLocationListener(myListener); // 注册监听函数


		// baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图

		// baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图
		// baiduMap.setTrafficEnabled(true); //开启交通图
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		id=getIntent().getIntExtra("id", 0);
		this.setLocationOption(); // 设置定位参数
		locationClient.start(); // 开始定位
		http=new HttpManager(this);
		http.setHttpCallBackListener(listener);
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		tv_signin.setOnClickListener(this);
	}

	/**
	 * 设置定位参数
	 */
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开GPS
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向

		locationClient.setLocOption(option);
	}

	public BDLocationListener myListener = new BDLocationListener() {
		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				return;

			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			baiduMap.setMyLocationData(locData); // 设置定位数据

			if (isFirstLoc) {
				isFirstLoc = false;
				latitude=location.getLatitude()+"";
				longitude=location.getLongitude()+"";
				addres=location.getAddrStr();
				LogJY.d("ffff",
						"纬度 : " + location.getLatitude() + "经度 : "
								+ location.getLongitude() + "地址："
								+ location.getAddrStr());
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory
						.newLatLngZoom(ll, 18); // 设置地图中心点以及缩放级别
				// MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				baiduMap.animateMapStatus(u);
			}
		}
	};

	// 三个状态实现地图生命周期管理
	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		locationClient.stop();
		baiduMap.setMyLocationEnabled(false);
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
		mapView = null;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.tv_signin:
				if(longitude.equals("")&&latitude.equals("")&&addres.equals("")){
					showToast("获取地址失败！");
				}else{
					try {
						http.doPost(ConstantsUrl.applicationSignIn(id+"", longitude, latitude, URLEncoder.encode(addres,"utf-8")), 1, false);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
		}
	}
	HttpCallBackListener listener =new HttpCallBackListener(){

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			showToast(result.getMsg());
			if(result.getResult()==1){
				//FieldPersonnelSignActivity.instance.finish();
				Intent intent = new Intent(SignInMapActivity.this,FieldPersonnelSignActivity.class);
				intent.putExtra("applyId", id+"");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub

		}

	};

}

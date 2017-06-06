package com.lyp.yangxixi.missionmanagerfragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lyp.adapters.AlltaskAdapter;
import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.LoginBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.MainActivity;
import com.tjl.yangxixi.api.YangxixiApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//所有任务
public class AllMissionFragment extends OriginalFragment{
	View v;

	private XRecyclerView mRecyclerView;
	private AlltaskAdapter mAdapter;
	private GridLayoutManager mGridLayoutManager;
	private List<AllTaskBean.DataBean> mList = new ArrayList<>();
	private LoginBean.DataBean bean;
	private TextView mCounts;
	//定义一个页码为1
	int pages=1;

	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_allmission, null,false);
		init();
		return v;
	}

	public void init(){
		mCounts = (TextView) v.findViewById(R.id.tv_counts);
		mRecyclerView = (XRecyclerView) v.findViewById(R.id.grid_recycler);
		mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		mAdapter = new AlltaskAdapter(mList);
		// 实例化 RecyclerView Adapter
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new AlltaskAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
//				Intent intentcar = new Intent(getActivity(),AssignmentdetailsCarActivity.class);
////				Bundle bundle = new Bundle();
////				bundle.putSerializable("databean",mList.get(0));
////				intentcar.putExtras(bundle);
//				startActivity(intentcar);
				Toast.makeText(getActivity(),"sdsd",Toast.LENGTH_SHORT).show();
			}
		});
		bean = ((MainActivity)getActivity()).dataBean;
		mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
		try {
			taskRefresh(bean.getC_id(),bean.getServer_select(),pages);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(1000);
						try {
							taskRefresh(bean.getC_id(),bean.getServer_select(),pages);
						} catch (IOException e) {
							e.printStackTrace();
						}
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mRecyclerView.refreshComplete();
							}
						});
					}
				}).start();
			}

			@Override
			public void onLoadMore() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							taskLoadMore(bean.getC_id(),bean.getServer_select(),pages);
						} catch (IOException e) {
							e.printStackTrace();
						}
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mRecyclerView.loadMoreComplete();
							}
						});
					}
				}).start();
			}
		});

	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
	}

	/**
	 * 刷新
	 */
	public void taskRefresh(String c_id, String server_select, final int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<AllTaskBean> call = github.getAllTask(c_id, server_select,page);
		call.enqueue(new Callback<AllTaskBean>() {
			@Override
			public void onResponse(Call<AllTaskBean> call, final Response<AllTaskBean> response) {
                if (response.body().getResult()== 1 ) {
					mCounts.setText(response.body().getCounts());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mList.addAll(0, response.body().getData());
                            mAdapter.notifyDataSetChanged();
							if (response.body().getData().size() >= 1) {
								pages++;
							} else {
								Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
							}
                        }
                    });
                }else {
                    Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
                }
			}
			@Override
			public void onFailure(Call<AllTaskBean> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 加载
	 */
	public void taskLoadMore(String c_id,String server_select,int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<AllTaskBean> call = github.getAllTask(c_id, server_select,page);
		call.enqueue(new Callback<AllTaskBean>() {
			@Override
			public void onResponse(Call<AllTaskBean> call, final Response<AllTaskBean> response) {
				if (response.body().getResult()== 1 ) {
					mCounts.setText(response.body().getCounts());
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mList.addAll(response.body().getData());
							mAdapter.notifyDataSetChanged();
							if (pages >= 1) {
								pages--;
							} else {
								Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
							}
						}
					});
				}else {
					Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFailure(Call<AllTaskBean> call, Throwable t) {
				Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}

}

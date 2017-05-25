package com.tjl.yangxixi.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.utils.uitl;

import java.util.ArrayList;

//销售员的线索报告
public class ReportFragment extends OriginalFragment implements OnClickListener{

    View v;
    private Button mYear,mMonth,mDay,mSelect;
    private ListView mListview;

    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    public View createView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        v= inflater.inflate(R.layout.fragment_jl_trailreport, null,true);
        initview();
        return v;
    }

    private void initview(){
        mYear = (Button) v.findViewById(R.id.btn_year);
        mMonth = (Button) v.findViewById(R.id.btn_month);
        mDay = (Button) v.findViewById(R.id.btn_day);
        mSelect = (Button) v.findViewById(R.id.btn_select);
//		mListview = (ListView) v.findViewById(R.id.listview);
        mYear.setOnClickListener(this);
        mMonth.setOnClickListener(this);
        mDay.setOnClickListener(this);
        mSelect.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.btn_year:
                year();
                break;
            case R.id.btn_month:
                month();
                break;
            case R.id.btn_day:
                day();
                break;
        }
    }

    public void year(){
        final String[] provices = new String[] { "2011", "2012", "2013", "2014", "2015","2016","2017","2018","2019" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("年份选择");//可以为空
        /*！注意这里的方法以及参数哟！"0"表示弹出对话框时，第一个是被选中的*/
        builder.setSingleChoiceItems(provices, 0,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(),
                                "选择的年份是：" + provices[arg1], Toast.LENGTH_SHORT)
                                .show();
                                /*提醒测试作用*/
                        mYear.setText(provices[arg1]);
                    }
                });
        builder.create().show();

    }
    public void month(){
        final String[] provices = new String[] { "1", "2", "3", "4", "5","6", "7", "8", "8", "10" ,"11","12" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("月份选择");//可以为空
        /*！注意这里的方法以及参数哟！"0"表示弹出对话框时，第一个是被选中的*/
        builder.setSingleChoiceItems(provices, 0,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(),
                                "选择的月份是：" + provices[arg1], Toast.LENGTH_SHORT)
                                .show();
                                /*提醒测试作用*/
                        mMonth.setText(provices[arg1]);
                    }
                });
        builder.create().show();

    }
    public void day(){
        uitl uitl=new uitl();
        int ri=Integer.parseInt(uitl.calDays(mYear.getText().toString().trim(),mMonth.getText().toString().trim()));
        Toast.makeText(getActivity(),
                ri+"", Toast.LENGTH_SHORT)
                .show();
        ArrayList<String> mList=new ArrayList();
        final String[] str=new String[ri];
        for (int i=0;i<ri; i++){
            mList.add((i+1)+"");
            str[i]=(i+1)+"";
        }
        for (int i=0;i<mList.size();i++){
            Log.e("OkHttp",mList.get(i));
        }
        final int size =mList.size();
//        final String[] arr = (String[])mList.toArray(new String[size]);//数组转换为string
//        final String[] provices = new String[] { "5", "15", "30", "16", "18" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("日选择");//可以为空
        /*！注意这里的方法以及参数哟！"0"表示弹出对话框时，第一个是被选中的*/
        builder.setSingleChoiceItems(str, 0,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(),
                                "选择的日期是：" + str[arg1], Toast.LENGTH_SHORT)
                                .show();
                                /*提醒测试作用*/
                        mDay.setText(str[arg1]);
                    }
                });
        builder.create().show();
    }
}

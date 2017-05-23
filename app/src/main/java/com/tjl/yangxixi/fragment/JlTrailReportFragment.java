package com.tjl.yangxixi.fragment;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.utils.uitl;

//销售经理的线索报告
public class JlTrailReportFragment extends OriginalFragment implements OnClickListener{

    View v;
    private Button mYear,mMonth,mDay,mSelect;
    private ListView mListview;

    @Override
    protected void lazyLoad() {
        // TODO Auto-generated method stub

    }

    @Override
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

//    /**
//     * 显示Pop窗口
//     * @param context
//     */
//    private void showpop(final Context context) {
//        View view = View.inflate(context, R.layout.popwindow_year,
//                null);
//        // 最后一个参数false 代表：不与其余布局发生交互， true代表：可以与其余布局发生交互事件
//        final PopupWindow popWindow = new PopupWindow(view,
//                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, false) {
//            // 重写popupWindow消失时事件
//            @Override
//            public void dismiss() {
//                // 在pop消失之前，给咱们加的view设置背景渐变出场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
//                viewBg.startAnimation(AnimationUtils.loadAnimation(context,
//                        R.anim.anim_bookshelf_folder_editer_exit));
//                viewBg.setVisibility(View.GONE);
//                super.dismiss();
//            }
//        };
//        // 设置Pop入场动画效果
//        popWindow.setAnimationStyle(R.style.bottom_PopWindowAnimation);
//        // 设置Pop响应内部区域焦点
//        popWindow.setFocusable(true);
//        // 设置Pop响应外部区域焦点
//        popWindow.setOutsideTouchable(true);
//        // 设置PopupWindow弹出软键盘模式（此处为覆盖式）
//        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        // 响应返回键必须的语句
//        popWindow.setBackgroundDrawable(new BitmapDrawable());
//        // 在显示pop之前，给咱们加的view设置背景渐变入场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
//        viewBg.setVisibility(View.VISIBLE);
//        viewBg.startAnimation(AnimationUtils.loadAnimation(context,
//                R.anim.anim_bookshelf_folder_editer_enter));
//        // 依附的父布局自己设定，我这里为了方便，这样写的。
//        popWindow.showAtLocation(viewBg, Gravity.BOTTOM, 0, 0);
//    }

}

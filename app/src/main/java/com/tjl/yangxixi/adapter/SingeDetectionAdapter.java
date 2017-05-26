package com.tjl.yangxixi.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lyp.jsonbean.JlSingeDetectionBean;

/**
 * 作者:ChenXi 时间:2017/5/26 18: 32
 */
//抢单(检测)
public class SingeDetectionAdapter extends BaseQuickAdapter<JlSingeDetectionBean.DataBean,BaseViewHolder>{


    public SingeDetectionAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, JlSingeDetectionBean.DataBean item) {

    }
}

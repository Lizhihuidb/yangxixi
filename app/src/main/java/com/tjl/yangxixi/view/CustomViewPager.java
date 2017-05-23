package com.tjl.yangxixi.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 禁止滑动的VIEWPAGER
 * @author tao
 *
 */
public class CustomViewPager extends ViewPager{
    public CustomViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}

package com.tjl.yangxixi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class OriginalFragment extends Fragment{
    private View qxFragmentView;
    protected boolean isVisible;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if(qxFragmentView==null){
            qxFragmentView=createView(inflater, container, savedInstanceState);
        }else{
            if(qxFragmentView.getParent()!=null&&qxFragmentView.getParent() instanceof ViewGroup){
                ViewGroup vg=(ViewGroup) qxFragmentView.getParent();
                vg.removeView(qxFragmentView);
            }
        }
        return qxFragmentView;
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}
    public abstract View createView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) ;



}

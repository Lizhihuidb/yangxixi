package com.lyp.yangxixi.missionmanagerfragment;

import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllMissionFragment extends OriginalFragment{
	
	
	View v;
	
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 v= inflater.inflate(R.layout.fragment_allmission, null,true);
				

		return v;
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}

}

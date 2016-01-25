package com.rmc.gaiya.fragment;

import com.rmc.gaiya.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeviceListFragment extends Fragment{


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View mView = inflater.inflate(R.layout.fragemnt_list, container, false);
	
		return mView;
	}
	
}

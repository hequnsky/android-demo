package com.djf.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> allFragment;
	
	public FragmentAdapter(FragmentManager fm,List<Fragment> allFragment) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.allFragment=allFragment;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return allFragment.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allFragment.size();
	}

	
}

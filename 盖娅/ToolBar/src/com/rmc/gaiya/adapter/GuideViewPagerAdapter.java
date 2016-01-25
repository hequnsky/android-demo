package com.rmc.gaiya.adapter;


import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author hq 功能描述：ViewPager适配器，用来绑定数据和view
 */
public class GuideViewPagerAdapter extends PagerAdapter {

	private List<View> views;
	private Context context;

	public GuideViewPagerAdapter (List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		return views.get(position);

	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

}
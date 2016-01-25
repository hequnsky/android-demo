package com.rmc.gaiya.adapter;


import com.rmc.gaiya.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Myadpater2  extends BaseAdapter{
	Context context;
	LayoutInflater inflater;
	private int mPlayingPosition;

	public void setPlayingPosition(int position) {
		mPlayingPosition = position;
	}
	
	public int getPlayingPosition() {
		return mPlayingPosition;
	}
	
	public Myadpater2(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder ;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_music,  null);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}
	static class ViewHolder {
	
		
	}

}

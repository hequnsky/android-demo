package com.rmc.gaiya.adapter;

import com.rmc.gaiya.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListInfoAdapter  extends BaseAdapter{
	Context context;
	LayoutInflater inflater;
	
	public ListInfoAdapter (Context context) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			return convertView = inflater.inflate(R.layout.list_music, null);
	}
}
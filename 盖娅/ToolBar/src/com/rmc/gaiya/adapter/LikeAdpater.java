package com.rmc.gaiya.adapter;


import java.util.List;

import com.rmc.gaiya.activity.R;
import com.rmc.gaiya.bean.LikeBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LikeAdpater  extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<LikeBean> data;
	private int mStart,mEnd;
	public static String [] URLS;
	private boolean mFirstIn;
	
	public LikeAdpater(Context context,List<LikeBean> data) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.data=data;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("null")
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			convertView = inflater.inflate(R.layout.list_love, null);
			holder=new ViewHolder();
			holder.music_icon=(ImageView)convertView.findViewById(R.id.music_icon);
			holder.music_like=(ImageView) convertView.findViewById(R.id.music_like);
			holder.music_names=(TextView) convertView.findViewById(R.id.music_names);
			convertView.setTag(holder);
		
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.music_names.setText(data.get(position).getMediaName());
		return convertView;
			
	}
 
class ViewHolder{
	ImageView music_like,music_icon;
	TextView music_names;
	
}
}

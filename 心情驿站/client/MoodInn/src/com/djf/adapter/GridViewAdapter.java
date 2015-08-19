package com.djf.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.djf.moodinn.R;

public class GridViewAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private List<Bitmap> allData;
	
	public GridViewAdapter(Context context,List<Bitmap> allImages) {
		// TODO Auto-generated constructor stub
		layoutInflater=LayoutInflater.from(context);
		this.allData=allImages;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(contentView==null){
			viewHolder=new ViewHolder();
			contentView=layoutInflater.inflate(R.layout.gridview_item, null);
			viewHolder.imageView=(ImageView)contentView.findViewById(R.id.gridItemView);
			contentView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)contentView.getTag();
		}
		
		viewHolder.imageView.setImageBitmap(allData.get(arg0));
		return contentView;
	}
	
	static class ViewHolder{
		
		private ImageView imageView;
	}

}

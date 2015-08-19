package com.djf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djf.bean.Comment;
import com.djf.moodinn.R;

public class MainListViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private List<Comment> allComment;
	
	
	public MainListViewAdapter(Context context,List<Comment> allComments) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.allComment=allComments;
		layoutInflater=LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allComment.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allComment.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LinearLayout layout = null;
		
		ViewHolder viewHolder = null;
		if (layout == null) {
			layout = (LinearLayout) layoutInflater.inflate(
					R.layout.pinglun_listview_item, null);
			
			viewHolder=new ViewHolder();
			viewHolder.tvName=(TextView)layout.findViewById(R.id.pinglunName);
			viewHolder.tvContent=(TextView)layout.findViewById(R.id.pinglunContent);
		} else {
			viewHolder = (ViewHolder) layout.getTag();
		}
		
		viewHolder.tvContent.setText(allComment.get(arg0).getComment_detail());
		viewHolder.tvName.setText(allComment.get(arg0).getComment_name());
		return layout;
	}

	private static class ViewHolder{
		
		private TextView tvName;
		private TextView tvContent;
		
	}
	
}

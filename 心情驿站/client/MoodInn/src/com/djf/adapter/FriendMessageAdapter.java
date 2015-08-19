package com.djf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djf.bean.FriendMessageBean;
import com.djf.moodinn.R;

public class FriendMessageAdapter extends BaseAdapter {

	private List<FriendMessageBean> allMessage;
	private LayoutInflater layoutInflater;
	private Context context;
	
	
	public FriendMessageAdapter(Context ctx,List<FriendMessageBean> allMessage) {
		// TODO Auto-generated constructor stub
		this.allMessage=allMessage;
		this.context=ctx;
		layoutInflater=LayoutInflater.from(ctx);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allMessage.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allMessage.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		RelativeLayout layoutView=null;
		ViewHolder viewHolder=null;
		if(layoutView==null){
			viewHolder=new ViewHolder();
			layoutView=(RelativeLayout) layoutInflater.inflate(R.layout.system_message_item, null);
			viewHolder.tvName=(TextView)layoutView.findViewById(R.id.tvSystemName);
			viewHolder.tvContent=(TextView)layoutView.findViewById(R.id.tvContent);
			viewHolder.tvTime=(TextView)layoutView.findViewById(R.id.tvTime);
			viewHolder.tvResultCode=(TextView)layoutView.findViewById(R.id.tvResult);
		}else{
			viewHolder=(ViewHolder)layoutView.getTag();
		}
		
		FriendMessageBean bean=allMessage.get(arg0);
		viewHolder.tvName.setText(bean.getAccept_name());
		viewHolder.tvResultCode.setText(bean.getSend_code());
		viewHolder.tvTime.setText(bean.getSend_time());
		viewHolder.tvContent.setText(bean.getMessage_content());
		return layoutView;
	}
	
	static class ViewHolder{
		
		private TextView tvName;
		
		private TextView tvContent;
		
		private TextView tvTime;
		
		private TextView tvResultCode;
		
		
	}

	
}

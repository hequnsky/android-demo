package com.rmc.gaiya.adapter;

import java.util.List;

import com.rmc.gaiya.activity.R;
import com.rmc.gaiya.bean.IPBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class IPAdapter  extends BaseAdapter{
	List<IPBean> data;
	Context context;
	LayoutInflater inflater;
	
	public IPAdapter(Context context,List<IPBean> data) {
		this.data=data;
		this.context=context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView = inflater.inflate(R.layout.ipitem, null);  
			holder.HostName=(TextView) convertView.findViewById(R.id.host_name);
			holder.HostMac=(TextView) convertView.findViewById(R.id.mac_name);
			holder.IsConneted=(TextView) convertView.findViewById(R.id.line);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}		  
		holder.HostName.setText(data.get(position).getHostName());
		holder.HostMac.setText(data.get(position).getHostMac());
		holder.IsConneted.setText(data.get(position).getIsConneted());
	
		return convertView;  
	}
class ViewHolder{
	TextView HostName,HostMac,IsConneted;
	
}

}
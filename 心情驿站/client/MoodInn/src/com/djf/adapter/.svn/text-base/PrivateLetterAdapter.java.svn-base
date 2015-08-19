package com.djf.adapter;

import java.util.List;

import com.djf.bean.PrivateLetter;
import com.djf.constants.HttpConstants;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.view.CircleImageView;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PrivateLetterAdapter extends BaseAdapter{
	
	private LayoutInflater layoutInflater;
	private Context context;
	private List<PrivateLetter> dataList;
	private AsynImageLoader asynImageLoader = new AsynImageLoader();
	
	
	public PrivateLetterAdapter(Context context, List<PrivateLetter> dataList) {
		// TODO Auto-generated constructor stub
		layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		PrivateLetter privateLetter = dataList.get(position);
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.system_private_letter_item, parent,
					false);
			viewHolder = new ViewHolder();
			viewHolder.private_petter_userIcon = (CircleImageView) convertView.findViewById(R.id.private_petter_userIcon);
			viewHolder.text_privateLetter_userName = (TextView) convertView.findViewById(R.id.text_privateLetter_userName);
			viewHolder.text_privateLetter_content = (TextView) convertView.findViewById(R.id.text_privateLetter_content);
			viewHolder.text_privateLetter_city = (TextView) convertView.findViewById(R.id.text_privateLetter_city);
			viewHolder.text_privateLetter_date = (TextView) convertView.findViewById(R.id.text_privateLetter_date);
			viewHolder.text_private_letter_sex = (TextView) convertView.findViewById(R.id.text_private_letter_sex);
			viewHolder.text_private_letter_age = (TextView) convertView.findViewById(R.id.text_private_letter_age);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.text_privateLetter_userName.setText(privateLetter.getUser().getName());
		viewHolder.text_privateLetter_content.setText(privateLetter.getMessage_content());
		viewHolder.text_privateLetter_city.setText(privateLetter.getUser().getCity());
		viewHolder.text_privateLetter_date.setText(privateLetter.getMessage_date());
		viewHolder.text_private_letter_sex.setText(privateLetter.getUser().getSex().equals("0")?"男":"女");
		viewHolder.text_private_letter_age.setText(privateLetter.getUser().getAge());
		if (!TextUtils.isEmpty(privateLetter.getUser().getImage())) {
			asynImageLoader.showImageAsyn(viewHolder.private_petter_userIcon, HttpConstants.HTTP_REQUEST
					+ privateLetter.getUser().getImage(), R.drawable.ic_launcher);
		}
		return convertView;
	}
	
	public class ViewHolder {
		
		CircleImageView private_petter_userIcon;
		TextView text_privateLetter_userName;
		TextView text_privateLetter_content;
		TextView text_privateLetter_city;
		TextView text_privateLetter_date;
		TextView text_private_letter_sex;
		TextView text_private_letter_age;
	}

}

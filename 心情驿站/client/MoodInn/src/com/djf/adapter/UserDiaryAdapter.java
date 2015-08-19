package com.djf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djf.bean.MoodInfoBean;
import com.djf.constants.HttpConstants;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.util.LvHeightUtil;
import com.djf.util.UnitTransformUtil;
import com.djf.view.NoScrollGridView;

public class UserDiaryAdapter extends BaseAdapter {

	private List<MoodInfoBean> allMoodInfoBean;
	private Context context;
	private LayoutInflater layoutInflater;
	List<String> allString;
	AsynImageLoader asynImageLoader = new AsynImageLoader();
	public UserDiaryAdapter(Context context,List<MoodInfoBean> allMoodInfoBean) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.allMoodInfoBean=allMoodInfoBean;
		layoutInflater=LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allMoodInfoBean.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allMoodInfoBean.get(arg0);
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
		MoodInfoBean bean=allMoodInfoBean.get(arg0);
		RelativeLayout relLayout=null;
		ViewHolder viewHolder;
		if(relLayout==null){
			viewHolder=new ViewHolder();
			relLayout=(RelativeLayout)layoutInflater.inflate(R.layout.user_alldiary, null);
			viewHolder.imageView=(ImageView)relLayout.findViewById(R.id.iv_leftlogo);
			viewHolder.userName=(TextView)relLayout.findViewById(R.id.allDiaryUserNmae);
			viewHolder.diaryTime=(TextView)relLayout.findViewById(R.id.diaryTime);
			viewHolder.diaryContent=(TextView)relLayout.findViewById(R.id.diaryContent);
			viewHolder.diaryGridView=(NoScrollGridView)relLayout.findViewById(R.id.myGridView);
			viewHolder.diaryPingLun=(ListView)relLayout.findViewById(R.id.pinglunListView);
			relLayout.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)relLayout.getTag();
		}
		
		viewHolder.userName.setText(bean.getUserName());
		viewHolder.diaryContent.setText(bean.getContent());
		viewHolder.diaryTime.setText(bean.getDate()+"/"+bean.getTime());
		
		
		asynImageLoader.showImageAsyn(viewHolder.imageView, HttpConstants.HTTP_REQUEST
				+ bean.getUsreIcon(), R.drawable.ic_launcher);
		
		String image_0 = bean.getImgone();
		String image_1 = bean.getImgtwo();
		String image_2 = bean.getImgthree();
		String image_3 = bean.getImgfour();
		String image_4 = bean.getImgfive();
		String image_5 = bean.getImgsix();

		allString = new ArrayList<String>();
		
		/**
		 * 对gridview 图片显示做的操作
		 */
		if (image_0 != null) {
			allString.add(image_0);
		}
		if (image_1 != null) {
			allString.add(image_1);
		}
		if (image_2 != null) {
			allString.add(image_2);
		}
		if (image_3 != null) {
			allString.add(image_3);
		}
		if (image_4 != null) {
			allString.add(image_4);
		}
		if (image_5 != null) {
			allString.add(image_5);
		}

		if (allString.size() > 0) {
			
			
			MainGridViewAdapter mainGridViewAdapter = new MainGridViewAdapter(
					context, allString);
			
			//说明有两行
			if(allString.size()>3){
				
				LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewHolder.diaryGridView.getLayoutParams(); // 取控件mGrid当前的布局参数
				linearParams.height = UnitTransformUtil.dip2px(context, 240);// 
				viewHolder.diaryGridView.setLayoutParams(linearParams);
				viewHolder.diaryGridView.setVerticalSpacing(1);
			}
			viewHolder.diaryGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			viewHolder.diaryGridView.setAdapter(mainGridViewAdapter);
		}
		
		 if(null!=bean.getAllComment()&&bean.getAllComment().size()!=0){
			  MainListViewAdapter pinglunAdapter=new MainListViewAdapter(context, bean.getAllComment()); 
			  viewHolder.diaryPingLun.setAdapter(pinglunAdapter);
					  LvHeightUtil.setListViewHeightBasedOnChildren(viewHolder.diaryPingLun);
		  }
		return relLayout;
	}
	
	static class ViewHolder{
		//用户图像
		private ImageView imageView;
		
		//用户昵称
		private TextView userName;
		
		//心情时间
		private TextView diaryTime;
		
		//心情内容
		private TextView diaryContent;
		
		//心情图片
		private NoScrollGridView diaryGridView;
		
		//心情评论
		private ListView diaryPingLun;
		
	}
	

}

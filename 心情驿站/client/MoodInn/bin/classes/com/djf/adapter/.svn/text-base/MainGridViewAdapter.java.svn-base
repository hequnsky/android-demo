package com.djf.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.djf.constants.HttpConstants;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;

public class MainGridViewAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private List<String> allImageUrl;
	private Context ctx;
	AsynImageLoader asynImageLoader = new AsynImageLoader();
	
	//暂且用bitmap加载图片
	private FinalBitmap finalBitmap; 
	
	public MainGridViewAdapter(Context context,List<String> allImageUrl) {
		// TODO Auto-generated constructor stub
		this.ctx=context;
		this.allImageUrl=allImageUrl;
		layoutInflater=LayoutInflater.from(ctx);
		
		finalBitmap=FinalBitmap.create(context);
		finalBitmap.configLoadingImage(R.drawable.ic_launcher);
		finalBitmap.configBitmapLoadThreadSize(5);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allImageUrl.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allImageUrl.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings({ "unused", "null" })
	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		RelativeLayout view=null;
		ViewHolder viewHolder;
		if(view==null){
			view=(RelativeLayout) layoutInflater.inflate(R.layout.main_gridview_item, null);
			viewHolder=new ViewHolder();
			viewHolder.imageView=(ImageView)view.findViewById(R.id.main_gridview_imageView);
			view.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)view.getTag();
		}
		/*asynImageLoader.showImageAsyn(viewHolder.imageView, HttpConstants.HTTP_REQUEST
				+ allImageUrl.get(arg0), R.drawable.ic_launcher);*/
		
		finalBitmap.display(viewHolder.imageView, HttpConstants.HTTP_REQUEST
				+ allImageUrl.get(arg0));
		return view;
	}
	
	private static class ViewHolder{
		
		private ImageView imageView;
	}

}

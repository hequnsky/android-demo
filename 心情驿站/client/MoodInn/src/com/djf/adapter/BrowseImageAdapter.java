package com.djf.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.djf.moodinn.R;

public class BrowseImageAdapter extends PagerAdapter {

	private List<View> allImageView;
	private Context context;
	private List<String> imageUrl;
	private Bitmap bitmap;


	public BrowseImageAdapter(Context context, List<View> allImageView,
			List<String> allImageUrl) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.allImageView = allImageView;
		this.imageUrl = allImageUrl;
	}

	/**
	 * 获取滑动控件的数量
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(allImageView!=null && allImageView.size()>0){
			return allImageView.size();
		}else{
			return 0;
		}
		
	}

	/**
	 * 判断显示的是否同一张图片
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	
	
	
	/**
	 * 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
	 */
	@Override
	public void destroyItem(ViewGroup view, int position, Object object) {
		view.removeView((View) object);
	}

	/**
	 * 当要显示的图片可以进行缓存的时候，会调用这个方法进行网络初始化
	 */
	@Override
	public Object instantiateItem(ViewGroup view, int position) {

		
		View v = allImageView.get(position);
		ImageView imageView = (ImageView) v
				.findViewById(R.id.viewPagerItemImage);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高
		Bitmap bitmap = BitmapFactory.decodeFile(imageUrl.get(position),
				options); // 此时返回bm为空
		options.inJustDecodeBounds = false;
		// 计算缩放比
		int be = (int) (options.outHeight / (float) 200);
		if (be <= 0)
			be = 1;
		options.inSampleSize = be;
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		bitmap = BitmapFactory.decodeFile(imageUrl.get(position), options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println(w + "" + h);
		Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);
		imageView.setImageBitmap(resizeBmp);
		view.addView(allImageView.get(position));
		return allImageView.get(position);
	}

	
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

}

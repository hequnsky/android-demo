package com.rmc.gaiya.tools;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImgTools {
	 ImageLoader loder;
	    static ImgTools imgTools;

	    public ImgTools() {
	        // 实例化ImageLoader
	        loder = ImageLoader.getInstance();
	    }
	    /**
	     * 使用单例模式对ImgTools进行实例化
	     * @return
	     */
	    public static ImgTools getInstance() {
	        if (imgTools == null) {
	            imgTools = new ImgTools();
	        }
	        return imgTools;
	    }

	    /**
	     * 获取正常的图片
	     * 
	     * @param imgUrl网络图片的地址
	     * @param img
	     * @param drawable
	     *            下载时，uri为空，加载错误时显示的图片
	     */
	    public ImageLoader getImgFromNetByUrl(String imgUrl, ImageView img,
	            int drawableId) {
	        ImageLoader loder = ImageLoader.getInstance();
	        DisplayImageOptions options = new DisplayImageOptions.Builder()                 
	                .showImageOnLoading(drawableId) // 设置正在下载是显示的图片
	                .showImageForEmptyUri(drawableId)// 设置图片Uri为空或是错误的时候显示的图片
	                .showImageOnFail(drawableId)// 设置图片加载/解码过程中错误时候显示的图片
	                .cacheInMemory(true)// 是否緩存都內存中
	                .cacheOnDisk(true)// 是否緩存到sd卡上
	                .considerExifParams(true) // 启用EXIF和JPEG图像格式
	                .bitmapConfig(Bitmap.Config.ARGB_4444)
	                .build();
	        loder.displayImage(imgUrl, img, options);
	        return loder;
	    }

	    /**
	     * 获取圆角的图片
	     * 
	     * @param imgUrl
	     *            网络图片的地址
	     * @param img
	     * @param drawable
	     *            下载时，uri为空，加载错误时显示的图片
	     * @param radius
	     *            圆角的弧度
	     */
	    public void getRadiusImgFromNetByUrl(String imgUrl, ImageView img,
	            int drawableId, int radius) {
	        ImageLoader loder = ImageLoader.getInstance();
	        DisplayImageOptions options = new DisplayImageOptions.Builder()
	                .showImageOnLoading(drawableId)
	                // 设置正在下载是显示的图片
	                .showImageForEmptyUri(drawableId)
	                // 设置图片Uri为空或是错误的时候显示的图片
	                .showImageOnFail(drawableId)
	                // 设置图片加载/解码过程中错误时候显示的图片
	                .cacheInMemory(true)
	                // 是否緩存都內存中
	                .cacheOnDisk(true)
	                // 是否緩存到sd卡上
	                .considerExifParams(true)
	                // 启用EXIF和JPEG图像格式
	                .displayer(new RoundedBitmapDisplayer(radius))
	                .bitmapConfig(Bitmap.Config.ARGB_4444)
	                .build();
	        loder.displayImage(imgUrl, img, options);
	    }
}
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
	        // ʵ����ImageLoader
	        loder = ImageLoader.getInstance();
	    }
	    /**
	     * ʹ�õ���ģʽ��ImgTools����ʵ����
	     * @return
	     */
	    public static ImgTools getInstance() {
	        if (imgTools == null) {
	            imgTools = new ImgTools();
	        }
	        return imgTools;
	    }

	    /**
	     * ��ȡ������ͼƬ
	     * 
	     * @param imgUrl����ͼƬ�ĵ�ַ
	     * @param img
	     * @param drawable
	     *            ����ʱ��uriΪ�գ����ش���ʱ��ʾ��ͼƬ
	     */
	    public ImageLoader getImgFromNetByUrl(String imgUrl, ImageView img,
	            int drawableId) {
	        ImageLoader loder = ImageLoader.getInstance();
	        DisplayImageOptions options = new DisplayImageOptions.Builder()                 
	                .showImageOnLoading(drawableId) // ����������������ʾ��ͼƬ
	                .showImageForEmptyUri(drawableId)// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
	                .showImageOnFail(drawableId)// ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
	                .cacheInMemory(true)// �Ƿ񾏴涼�ȴ���
	                .cacheOnDisk(true)// �Ƿ񾏴浽sd����
	                .considerExifParams(true) // ����EXIF��JPEGͼ���ʽ
	                .bitmapConfig(Bitmap.Config.ARGB_4444)
	                .build();
	        loder.displayImage(imgUrl, img, options);
	        return loder;
	    }

	    /**
	     * ��ȡԲ�ǵ�ͼƬ
	     * 
	     * @param imgUrl
	     *            ����ͼƬ�ĵ�ַ
	     * @param img
	     * @param drawable
	     *            ����ʱ��uriΪ�գ����ش���ʱ��ʾ��ͼƬ
	     * @param radius
	     *            Բ�ǵĻ���
	     */
	    public void getRadiusImgFromNetByUrl(String imgUrl, ImageView img,
	            int drawableId, int radius) {
	        ImageLoader loder = ImageLoader.getInstance();
	        DisplayImageOptions options = new DisplayImageOptions.Builder()
	                .showImageOnLoading(drawableId)
	                // ����������������ʾ��ͼƬ
	                .showImageForEmptyUri(drawableId)
	                // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
	                .showImageOnFail(drawableId)
	                // ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
	                .cacheInMemory(true)
	                // �Ƿ񾏴涼�ȴ���
	                .cacheOnDisk(true)
	                // �Ƿ񾏴浽sd����
	                .considerExifParams(true)
	                // ����EXIF��JPEGͼ���ʽ
	                .displayer(new RoundedBitmapDisplayer(radius))
	                .bitmapConfig(Bitmap.Config.ARGB_4444)
	                .build();
	        loder.displayImage(imgUrl, img, options);
	    }
}
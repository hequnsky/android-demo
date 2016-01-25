package com.rmc.gaiya.common;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.rmc.gaiya.bean.LoginBean;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private  LoginBean logindata;
	

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {

         File cacheDir = StorageUtils.getOwnCacheDirectory(
         getApplicationContext(), "Travel/"); // �����ڴ濨��·��

         ImageLoaderConfiguration config = new
         ImageLoaderConfiguration.Builder(
         context).threadPriority(Thread.NORM_PRIORITY - 2)// ���õ�ǰ�߳����ȼ�
         .denyCacheImageMultipleSizesInMemory() // ������ʾ��ͬ ��С��ͬһ��ͼƬ
         .diskCacheSize(50 * 1024 * 1024) // ����Sd���Ļ������ֵ
         .diskCache(new UnlimitedDiskCache(cacheDir))// sd������
         .memoryCache(new WeakMemoryCache()) // �ڴ滺��
         .tasksProcessingOrder(QueueProcessingType.LIFO).build();

         ImageLoader.getInstance().init(config);


    }
    public 	LoginBean getLoginBean(){
		return logindata;
    	
    }
    public void setLoginBean(LoginBean login){
    	this.logindata=login;
    	
    }
    
    
}

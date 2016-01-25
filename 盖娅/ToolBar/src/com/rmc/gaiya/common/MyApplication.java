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
         getApplicationContext(), "Travel/"); // 设置内存卡的路径

         ImageLoaderConfiguration config = new
         ImageLoaderConfiguration.Builder(
         context).threadPriority(Thread.NORM_PRIORITY - 2)// 设置当前线程优先级
         .denyCacheImageMultipleSizesInMemory() // 缓存显示不同 大小的同一张图片
         .diskCacheSize(50 * 1024 * 1024) // 本地Sd卡的缓存最大值
         .diskCache(new UnlimitedDiskCache(cacheDir))// sd卡缓存
         .memoryCache(new WeakMemoryCache()) // 内存缓存
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

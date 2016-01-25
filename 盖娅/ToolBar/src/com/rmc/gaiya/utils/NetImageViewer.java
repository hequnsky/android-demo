package com.rmc.gaiya.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public abstract class NetImageViewer {
	//网络图片通用下载器工具类
	// 1、子线程下载图片
	 
    // 2、子线程下载图片完成后，通知主线程更新UI
 
    /**
     * 3、下载完毕后，调用的方法
     * 
     * @param bitmap
     */
    public abstract void onImageLoaded(Bitmap bitmap);
 
    /**
     * 2、图片下载后，如何处理
     */
    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
		@Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            onImageLoaded(bitmap);
        }
    };
 
    /**
     * 1、到指定的path下载图片，然后发送消息
     * 
     * @param path 图片的路径
     * @throws IOException
     */
    public void downloadImage(final String path) {
 
        new Thread() {
            @Override
            public void run() {
 
                try {
 
                    // 1、获取url
                    URL url = new URL(path);
 
                    // 2、获取url的HttpURLConnection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 
                    // 3、设置连接的请求信息
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
 
                    // 4、如果服务器返回码为200
                    int code = conn.getResponseCode();
                    if (code == 200) {
 
                        // 5、获取输入流
                        InputStream in = conn.getInputStream();
 
                        // 6、将输入流转成Bitmap对象
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
 
                        // 7、发送消息
                        Message msg = new Message();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
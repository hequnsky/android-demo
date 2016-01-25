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
	//����ͼƬͨ��������������
	// 1�����߳�����ͼƬ
	 
    // 2�����߳�����ͼƬ��ɺ�֪ͨ���̸߳���UI
 
    /**
     * 3��������Ϻ󣬵��õķ���
     * 
     * @param bitmap
     */
    public abstract void onImageLoaded(Bitmap bitmap);
 
    /**
     * 2��ͼƬ���غ���δ���
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
     * 1����ָ����path����ͼƬ��Ȼ������Ϣ
     * 
     * @param path ͼƬ��·��
     * @throws IOException
     */
    public void downloadImage(final String path) {
 
        new Thread() {
            @Override
            public void run() {
 
                try {
 
                    // 1����ȡurl
                    URL url = new URL(path);
 
                    // 2����ȡurl��HttpURLConnection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 
                    // 3���������ӵ�������Ϣ
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
 
                    // 4�����������������Ϊ200
                    int code = conn.getResponseCode();
                    if (code == 200) {
 
                        // 5����ȡ������
                        InputStream in = conn.getInputStream();
 
                        // 6����������ת��Bitmap����
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
 
                        // 7��������Ϣ
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
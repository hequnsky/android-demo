package com.djf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.djf.bean.Data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

public class UpdateManager {

	private static Context mContext;
	/* 下载包安装路径 */
	private final String savePath = "/sdcard/updatedemo/";
	private String saveFileName = savePath;
	private final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private static final int DOWN_ERROE = 3;
	private static int progress;
	private Thread downLoadThread;
	private Thread upDateThread;
	private boolean interceptFlag = false;
	private Button button;
	private Data data;
	
	public UpdateManager(Context context, Data data) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = data;
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				button.setText(progress + "%");
				break;
			case DOWN_OVER:
				installApk();
				break;
			case DOWN_ERROE:
				break;
			default:
				break;
			}
		};
	};
	
	/**
	 * 下载apk
	 * 
	 * @param url
	 */

	public void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {

				URL url = new URL(data.getApkpath());

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestProperty("Accept-Encoding", "identity");
				conn.connect();
				int length = conn.getContentLength();
				Log.e("length", length + "");
				InputStream is = conn.getInputStream();

				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				saveFileName = savePath + "MoodInn" + ".apk";
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				mHandler.sendEmptyMessage(DOWN_ERROE);
				e.printStackTrace();
			} catch (IOException e) {
				mHandler.sendEmptyMessage(DOWN_ERROE);
				e.printStackTrace();
			}
		}
	};
	
	/**
	 * 安装apk
	 * 
	 * @param url
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}

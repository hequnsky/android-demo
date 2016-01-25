package com.rmc.gaiya.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import com.rmc.gaiya.utils.CheckBoxListener;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class TCPclient {
	private Context context;
	InputStream in;
	PrintWriter printWriter = null;
	BufferedReader reader;
	public boolean isConnected = false;
	Socket mSocket = null;
	CheckBoxListener listener;
	Thread receiverThread;
	String result;
	public TCPclient(Context context) {
		this.context=context;
	}
  public void conn(final String ip,final String port){
	if (!isConnected) {
		new Thread(){
			@Override
			public void run() {
				Looper.prepare();
				try {
					mSocket = new Socket(ip, Integer.parseInt(port));
					showInfo("连接成功!");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}	
}
  /*
   * 发送数据
   */
 public void send(String msg) throws IOException{
	 
	    OutputStream outputStream = mSocket.getOutputStream();
		printWriter = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(outputStream,
						Charset.forName("gb2312"))));
		listener.setOutStream(printWriter);
		in = mSocket.getInputStream();
		if (printWriter == null || msg == null) {

			if (printWriter == null) {
				showInfo("连接失败!");
				return;
			}
			if (msg == null) {
				showInfo("连接失败!");
				return;
			}
		}

		printWriter.print(context);
		printWriter.flush();
 } 
/*
 * 显示信息
 */
	private void showInfo(String Message) {
		Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();

	}
	
	public String  getreceiverData() {
		receiverThread = new Thread(new MyReceiverRunnable());
		receiverThread.start();
		return result;
	}
	private class MyReceiverRunnable implements Runnable {

		public void run() {

			while (true) {

				
				if (isConnected) {
					if (mSocket != null && mSocket.isConnected()) {
						result = readFromInputStream(in);
					}
				}
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
	/*
	 * 
	 * 获取返回来的数据
	 */
	public String readFromInputStream(InputStream in) {
		int count = 0;
		byte[] inDatas = null;
		try {
			while (count == 0) {
				count = in.available();
			}
			inDatas = new byte[count];
			in.read(inDatas);
			return new String(inDatas, "gb2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

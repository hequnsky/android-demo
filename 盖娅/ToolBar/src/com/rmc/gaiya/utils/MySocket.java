package com.rmc.gaiya.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;

public class MySocket extends Thread {

	private Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	Handler handler;
	// 定义接收UI线程的Handler对象
	Handler revHandler;
	public MySocket(Handler handler) {
		this.handler = handler;
	}

	
	public MySocket(Socket socket) {
		this.socket = socket;
		try {
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//启动线程
		start();
		
	}
	
	@Override
	public void run() {
		//不断的读取读取服务器端推送过来的消息
		String content = null;
		while(true){
			try {
				if ((content = reader.readLine()) != null) {
					Message msg = new Message();
					msg.what = 0x123;
					msg.obj = content;
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMsg(String message) throws IOException{
		writer.write(message);
		writer.newLine();
		writer.flush();
	}
	
}


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
	// �������UI�̵߳�Handler����
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
		
		//�����߳�
		start();
		
	}
	
	@Override
	public void run() {
		//���ϵĶ�ȡ��ȡ�����������͹�������Ϣ
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
	 * ������Ϣ
	 * @param message
	 * @throws IOException
	 */
	public void sendMsg(String message) throws IOException{
		writer.write(message);
		writer.newLine();
		writer.flush();
	}
	
}


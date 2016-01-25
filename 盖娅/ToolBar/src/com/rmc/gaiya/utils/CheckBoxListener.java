package com.rmc.gaiya.utils;


import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckBoxListener implements OnCheckedChangeListener {

	private static final String tag = "CheckBoxListener";
	private String text = "";

	private PrintWriter writer = null;

	private Timer timer = null;
	private MyTask task = null;




	/**
	 * ��ȡ��ʱ��ʱ��.
	 * 
	 * @param time
	 */
	public int getTimerCycle(String time) {
		int timeCycle = 0;
		Log.i(tag, "--->>time = " + timeCycle);

		try {
			timeCycle = Integer.valueOf(time);

		} catch (Exception e) {
			timeCycle = -1;
			Log.i(tag, "--->>ʱ���쳣...");
		}
		return timeCycle;
	}

	/**
	 * ��ȡ�����.
	 * 
	 * @param printWriter
	 */
	public void setOutStream(PrintWriter printWriter) {
		this.writer = printWriter;
	}

	public void setSendText(String text) {
		this.text = text;

	}
	private class MyTask extends TimerTask {

		@Override
		public void run() {

			if (writer != null) {
				writer.write(text);
				writer.flush();
			}
		}
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
	}
}

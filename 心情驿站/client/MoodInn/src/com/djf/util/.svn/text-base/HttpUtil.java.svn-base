package com.djf.util;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.djf.callback.ResultCallback;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class HttpUtil {

	
	public static void doPost(final String reqUrl,final List<NameValuePair> params,ResultCallback callback){
		
		resultCallback=callback;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message=new Message();
				
				HttpPost httpPost=new HttpPost(reqUrl);
				try{
					httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					HttpResponse httpResponse;
					try{
						httpResponse=new DefaultHttpClient().execute(httpPost);
						if(httpResponse.getStatusLine().getStatusCode()==200){
							String result = EntityUtils.toString(httpResponse.getEntity());
							if(!result.isEmpty()){
								message.what=0;
								message.obj=result;
								myHandler.sendMessage(message);
							}else{
								message.what=1;
								myHandler.sendMessage(message);
							}
						}else{
							message.what=1;
							myHandler.sendMessage(message);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
	static Handler myHandler=new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what==1){
				resultCallback.getReslt("1");
			}else if(msg.what==0){
				String result=(String) msg.obj;
				resultCallback.getReslt(result);
			}
		};
	};
	
	static ResultCallback resultCallback=new ResultCallback() {
		
		@Override
		public void getReslt(String result) {
			// TODO Auto-generated method stub
		}
	};
}

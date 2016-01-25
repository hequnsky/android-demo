package com.rmc.gaiya.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.myandroid.ui.OkHttpClientManager;
import com.myandroid.ui.OkHttpClientManager.ResultCallback;
import com.rmc.gaiya.adapter.ListInfoAdapter;
import com.rmc.gaiya.bean.LikeBean;
import com.squareup.okhttp.Request;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class GaiyaListInfo extends Activity{
	// 请求接口地址
  private static final String Url = "http://192.168.1.226:89/api/LikeMedia";
	ListView list;
	ListInfoAdapter Adapter;
	List<LikeBean> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gaiya_musicinfo);
		list=(ListView) findViewById(R.id.list);
		new NewsAsyncTask().execute(Url);
	
		list.setAdapter(Adapter);
	}
	public void back(View v){
	    GaiyaListInfo.this.finish();
	}
	class NewsAsyncTask extends AsyncTask<String, Void,List<LikeBean>> {

		@Override
		protected  List<LikeBean> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return getJosnData(params[0]);
		}

	
	}
	public List<LikeBean> getJosnData(String Url) {
		// TODO Auto-generated method stub
		// 把请求参数以map键值对的形式存入到JSONObject中去
		JSONObject map = null;
		try {
			map = new JSONObject();
			map.put("LabelMarkupId", "5");
			map.put("PageNum", "10");
			map.put("PageCount", "10");
			data = new ArrayList<LikeBean>();
			okhttp_Post(Url, map.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return  data;
	}

				
	private void okhttp_Post(String url, String map) {
		OkHttpClientManager.postAsyn(url, map, new ResultCallback<List<LikeBean>>() {
			@Override
			public void onError(Request request, Exception e) {
				Log.i("INFO", "失败");
			}

			@Override
			public void onResponse( List<LikeBean> response) {
				
				for (int i = 0; i < 10; i++) {
					LikeBean like=new LikeBean();
					like.setMediaName(response.get(i).getMediaName());
					data.add(like);
				}
				Adapter=new ListInfoAdapter(GaiyaListInfo.this,data);
				list.setAdapter(Adapter);
			}

		});

	}
	}

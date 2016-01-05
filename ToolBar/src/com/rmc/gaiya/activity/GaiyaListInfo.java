package com.rmc.gaiya.activity;

import com.rmc.gaiya.adapter.ListInfoAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class GaiyaListInfo extends Activity{
	ListView list;
	ListInfoAdapter Adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gaiya_musicinfo);
		
		list=(ListView) findViewById(R.id.list);
		Adapter=new ListInfoAdapter(getApplicationContext());
		list.setAdapter(Adapter);
	}
	public void back(View v){
	    GaiyaListInfo.this.finish();
	}

}

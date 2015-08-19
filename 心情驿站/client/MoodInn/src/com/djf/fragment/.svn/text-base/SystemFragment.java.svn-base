package com.djf.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.djf.activity.SystemDetailAcitivity;
import com.djf.adapter.PrivateLetterAdapter;
import com.djf.bean.BasePrivateLetter;
import com.djf.bean.PrivateLetter;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.listview.XListView;
import com.djf.moodinn.R;
import com.djf.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SystemFragment extends Fragment {

	private LayoutInflater layoutInflater;
	private View mainView;
	private XListView private_letter;
	private PrivateLetterAdapter privateLetterAdapter;
	private List<PrivateLetter> privateLetterList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		layoutInflater = LayoutInflater.from(getActivity());
		mainView = layoutInflater.inflate(R.layout.system_fragment, null);
		initView(mainView);
		loadData();
		return mainView;
	}

	private void initView(View view) {
		private_letter = (XListView) mainView
				.findViewById(R.id.listview_private_letter);
		private_letter.setOnItemClickListener(itemClickListener);
	}
	
	private void setData(){
		privateLetterAdapter = new PrivateLetterAdapter(getActivity(),privateLetterList);
		private_letter.setAdapter(privateLetterAdapter);
	}

	private void loadData() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("acceptid", new SharedPreferenceDb(
				getActivity()).getUserId()));
		HttpUtil.doPost(HttpConstants.HTTP_QUERY_AllMESSAGE, params,
				new ResultCallback() {

					@Override
					public void getReslt(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Type type = new TypeToken<BasePrivateLetter>() {
						}.getType();
						BasePrivateLetter basePrivateLetter = gson.fromJson(result, type);
						if (basePrivateLetter != null) {
							privateLetterList = basePrivateLetter.getData();
							if (privateLetterList != null) {
								setData();
							}
						}
					}
				});
	}
	
	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), SystemDetailAcitivity.class);
			intent.putExtra("privateLetter", privateLetterList.get(position));
			startActivity(intent);
		}
	};
}

package com.djf.fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.djf.adapter.TimelineAdapter;
import com.djf.moodinn.R;

public class PrivateFragment extends Fragment {

	private LayoutInflater layoutInflater;
	private View mainView;
	private ListView myListView;
	List<String> data ;
	private TimelineAdapter timelineAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		layoutInflater=LayoutInflater.from(getActivity());
		mainView=layoutInflater.inflate(R.layout.private_fragment, null);
		
		
		myListView=(ListView)mainView.findViewById(R.id.listview);
		myListView.setDividerHeight(0);
		timelineAdapter = new TimelineAdapter(getActivity(), getData());
		myListView.setAdapter(timelineAdapter);
		return mainView;
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "这是第1行测试数据");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "这是第2行测试数据");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "这是第3行测试数据");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "这是第4行测试数据");
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("title", "这是第5行测试数据");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "这是第6行测试数据");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "这是第7行测试数据");
		list.add(map);
		return list;
	}

}

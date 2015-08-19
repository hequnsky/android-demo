package com.djf.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.view.TitleBar;

public class UserHelpActivity extends BaseActivity {

	private ExpandableListView expandableListView;
	
	private  List<String> groupArray;  
	private  List<List<String>> childArray;  

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.user_help);
		
		expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
		
		groupArray = new  ArrayList<String>();  
		childArray = new  ArrayList<List<String>>();  
		  
		groupArray.add("怎么使用？" );  
		groupArray.add("怎么修改主题?" );  
		groupArray.add("如何屏蔽他人消息?" );  
		groupArray.add("可以拉黑么?" );  
		  
		List<String> tempArray0 = new  ArrayList<String>();  
		tempArray0.add("第一dljfldfjldjfldjfldfjdlfjdlfjdlfjdlfjldfjldfjldjfldjf条" );  
		
		List<String> tempArray1 = new  ArrayList<String>();  
		tempArray1.add("fjdlf" );  
		
		List<String> tempArray2 = new  ArrayList<String>();  
		tempArray2.add("lfjldfjldfjldjfldjf条" );  
		
		List<String> tempArray3 = new  ArrayList<String>();  
		tempArray3.add("第一dljfl" );  
		
		childArray.add(tempArray0);
		childArray.add(tempArray1);
		childArray.add(tempArray2);
		childArray.add(tempArray3);
		
		expandableListView.setAdapter(new  ExpandableAdapter(UserHelpActivity.this ));  

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TitleBar titleBar = (TitleBar) findViewById(R.id.userHelpTitleBar);
		if (!"0".equals(String.valueOf(new SharedPreferenceDb(
				UserHelpActivity.this).getChangeTheme()))) {
			titleBar.setBackgroundColor(new SharedPreferenceDb(
					UserHelpActivity.this).getChangeTheme());
		} else {
			titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		}
		titleBar.showLeft("帮助",
				getResources().getDrawable(R.drawable.app_back),
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finish();
					}
				});
	}
	
	public class ExpandableAdapter extends BaseExpandableListAdapter {

		 Activity activity;  
	     
		    public  ExpandableAdapter(Activity a)  
		    {  
		        activity = a;  
		    }  
		    @Override
			public  Object getChild(int  groupPosition, int  childPosition)  
		    {  
		        return  childArray.get(groupPosition).get(childPosition);  
		    }  
		    @Override
			public  long  getChildId(int  groupPosition, int  childPosition)  
		    {  
		        return  childPosition;  
		    }  
		    @Override
			public  int  getChildrenCount(int  groupPosition)  
		    {  
		        return  childArray.get(groupPosition).size();  
		    }  
		    @Override
			public  View getChildView(int  groupPosition, int  childPosition,  
		            boolean  isLastChild, View convertView, ViewGroup parent)  
		    {  
		        String string = childArray.get(groupPosition).get(childPosition);  
		        return  getGenericView(string);  
		    }  
		    // group method stub   
		    @Override
			public  Object getGroup(int  groupPosition)  
		    {  
		        return  groupArray.get(groupPosition);  
		    }  
		    @Override
			public  int  getGroupCount()  
		    {  
		        return  groupArray.size();  
		    }  
		    @Override
			public  long  getGroupId(int  groupPosition)  
		    {  
		        return  groupPosition;  
		    }  
		    @Override
			public  View getGroupView(int  groupPosition, boolean  isExpanded,  
		            View convertView, ViewGroup parent)  
		    {  
		        String string = groupArray.get(groupPosition);  
		        return  getGenericView(string);  
		    }  
		    // View stub to create Group/Children 's View   
		    public  TextView getGenericView(String string)  
		    {  
		        // Layout parameters for the ExpandableListView   
		        AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(  
		                ViewGroup.LayoutParams.FILL_PARENT, 100 );  
		        TextView text = new  TextView(activity);  
		        text.setLayoutParams(layoutParams);  
		        // Center the text vertically   
		        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
		        // Set the text starting position   
		        text.setPadding(60 , 0 , 0 , 0 );  
		        text.setText(string);  
		        return  text;  
		    }  
		    @Override
			public  boolean  hasStableIds()  
		    {  
		        return  false ;  
		    }  
		    @Override
			public  boolean  isChildSelectable(int  groupPosition, int  childPosition)  
		    {  
		        return  true ;  
		    }  
	}

}

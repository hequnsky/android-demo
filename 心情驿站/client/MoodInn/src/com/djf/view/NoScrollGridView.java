package com.djf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义GridView
 * 防止跟listview 上下拉滚动冲动
 * @author Administrator
 *
 */
public class NoScrollGridView extends GridView {

	public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	
}

package com.rmc.gaiya.widget;

import com.rmc.gaiya.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class CustomListView extends ListView implements OnScrollListener {

	/**
	 * 底部加载更多布局
	 */
	private View footerView;

	/**
	 * 最后一个可见的item
	 */
	private int lastVisibleItem;

	/**
	 * listView 总的item的数量
	 */
	private int totalItemCount;

	/**
	 * 正在加载；
	 */
	private boolean isLoading;

	/**
	 * 接口对象
	 */
	private ILoadListener iLoadListener;

	public CustomListView(Context context) {
		// TODO Auto-generated method stub
		super(context);
	}

	public CustomListView(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		super(context, null);
	}

	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		super(context, attrs, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (lastVisibleItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
			if (!isLoading) {
				isLoading = true;
				footerView.setVisibility(View.VISIBLE);
				iLoadListener.loadmore();
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

		this.totalItemCount = totalItemCount;
		this.lastVisibleItem = firstVisibleItem + visibleItemCount;
	}

	/**
	 * 加载更多完成的方法
	 */
	public void loadComplete() {
		isLoading = false;
		footerView.setVisibility(View.GONE);
	}

	/**
	 * 实例化接口的方法
	 * 
	 * @param iLoadListener
	 */
	public void setInterface(ILoadListener iLoadListener) {
		this.iLoadListener = iLoadListener;
	}
	/**
	 * 初始化界面的方法
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		footerView = inflater.inflate(R.layout.footer_layout, null);
		footerView.setVisibility(View.GONE);
		this.addFooterView(footerView);
		this.setOnScrollListener(this);

	}

	public interface ILoadListener {
		public void loadmore();
	}
}

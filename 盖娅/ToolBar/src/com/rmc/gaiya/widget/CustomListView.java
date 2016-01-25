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
	 * �ײ����ظ��಼��
	 */
	private View footerView;

	/**
	 * ���һ���ɼ���item
	 */
	private int lastVisibleItem;

	/**
	 * listView �ܵ�item������
	 */
	private int totalItemCount;

	/**
	 * ���ڼ��أ�
	 */
	private boolean isLoading;

	/**
	 * �ӿڶ���
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
	 * ���ظ�����ɵķ���
	 */
	public void loadComplete() {
		isLoading = false;
		footerView.setVisibility(View.GONE);
	}

	/**
	 * ʵ�����ӿڵķ���
	 * 
	 * @param iLoadListener
	 */
	public void setInterface(ILoadListener iLoadListener) {
		this.iLoadListener = iLoadListener;
	}
	/**
	 * ��ʼ������ķ���
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

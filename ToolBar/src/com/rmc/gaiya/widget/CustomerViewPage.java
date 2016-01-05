package com.rmc.gaiya.widget;

import java.util.ArrayList;
import java.util.List;

import com.rmc.gaiya.activity.R;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class CustomerViewPage extends RelativeLayout implements Runnable {

	/**
	 * Ҫ��ʾ��ViewPage����
	 */
	private ViewPager viewPager;
	/**
	 * ���õײ�ָʾ�������ͼ
	 */
	private LinearLayout viewDots;
	/**
	 * viewDots�ϵ�ָʾ��
	 */
	private List<ImageView> dots;
	/**
	 * ViewPage��
	 */
	private List<View> views;
	/**
	 * ��ǰ��ʾ�ڼ���ͼ
	 */
	private int position = 0;
	/**
	 * �ɲ������Զ���ת��Ϊtrue���ִ���ʱ����ת��
	 */
	private boolean isContinue = true;
	/**
	 * �л�ʱ��/ms
	 */
	private long changeTime = 1500;

	/**
	 * �ײ�ָʾ��֮��ļ�࣬Ĭ��2dp
	 */
	private int dotsSpacing = 2;

	/**
	 * �ײ�ָʾ���С,Ĭ��7dp
	 */
	private int circleRadio = 10;

	/**
	 * �ֲ�ͼƬ�߳��Ƿ���
	 */
	private boolean isAlive = true;
	/**
	 * �ײ�ָʾ���ڸ�View(��viewDots��ͼ)��gravity����,Ĭ�����м�
	 * Gravity.RIGHT | Gravity.LEFT | Gravity.CENTER
	 */
	private int gravity = Gravity.CENTER;

	public CustomerViewPage(Context context) {
		this(context, null);
	}

	public CustomerViewPage(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomerViewPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		/**
		 * ��ʼ��ViewPage��ͼ
		 */
		viewPager = new ViewPager(getContext());
		LayoutParams viewPagerlp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		addView(viewPager, viewPagerlp);
		/**
		 * ��ʼ���ײ�ָʾ����ͼ
		 */
		viewDots = new LinearLayout(getContext());
		LayoutParams viewDotslp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		viewDotslp.addRule(ALIGN_PARENT_BOTTOM);
		viewDotslp.bottomMargin = dpTopx(5);
		viewDots.setGravity(gravity);
		addView(viewDots, viewDotslp);
	}

	@SuppressWarnings("deprecation")
	public void setViewPageViews(List<View> views) {
		this.views = views;
		// ��ʼ���ײ���Բ
		addDots(views.size());

		viewPager.setAdapter(new CustomerViewPageAdapter());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				position = index;
				for (int i = 0; i < dots.size(); i++) {
					if (position == i) {
						dots.get(i).setBackgroundResource(
								R.drawable.dot_focused);
					} else {
						dots.get(i)
								.setBackgroundResource(R.drawable.dot_normal);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		viewPager.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(this).start();
	}

	private void addDots(int size) {
		dots = new ArrayList<ImageView>();
		for (int i = 0; i < size; i++) {
			ImageView dot = new ImageView(getContext());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					dpTopx(circleRadio), dpTopx(circleRadio));
			params.setMargins(dpTopx(dotsSpacing), 0, dpTopx(dotsSpacing), 0);
			dot.setLayoutParams(params);
			if (i == 0) {
				dot.setBackgroundResource(R.drawable.dot_focused);
			} else {
				dot.setBackgroundResource(R.drawable.dot_normal);
			}
			dots.add(dot);
			viewDots.addView(dot);
		}
	}

	class CustomerViewPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return views == null ? 0 : views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			if (views.get(position).getParent() != null) {
				((ViewGroup) views.get(position).getParent()).removeView(views
						.get(position));
			}
			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

	}

	Handler pagerHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		};
	};

	@Override
	public void run() {
		while (isAlive) {
			if (isContinue) {
				pagerHandler.sendEmptyMessage(position);
				position = (position + 1) % views.size();
				try {
					Thread.sleep(changeTime);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private int dpTopx(int dp) {
		float scale = getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public void stop() {
		isAlive = false;
	}
}

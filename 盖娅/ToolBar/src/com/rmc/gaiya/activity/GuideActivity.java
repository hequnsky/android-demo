package com.rmc.gaiya.activity;

import java.util.ArrayList;
import java.util.List;

import com.rmc.gaiya.adapter.GuideViewPagerAdapter;
import com.rmc.gaiya.utils.SharedPreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GuideActivity extends Activity implements OnPageChangeListener {

	private ViewPager vp;
	private GuideViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView[] dots;
	private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };
	private Button start_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		initViews();
		initDots();
	}

	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);

		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.guide_view01, null));
		views.add(inflater.inflate(R.layout.guide_view02, null));
		views.add(inflater.inflate(R.layout.guide_view03, null));

		vpAdapter = new GuideViewPagerAdapter(views, this);
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		// �±��0��ʼ�����Ե�����ҳ����get(2)��
		start_btn = (Button) views.get(2).findViewById(R.id.startBtn);
		start_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SharedPreferenceUtil.set(GuideActivity.this, "Login", "isFist", "true");
				Intent i = new Intent(GuideActivity.this, LoginActivity.class);
				startActivity(i);
				finish();
			}
		});
		vp.setOnPageChangeListener(this);
	}

	/** ѭ�����õ� */
	private void initDots() {
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) findViewById(ids[i]);
		}
	}

	@Override  /** ����״̬�ı��ʱ�� */
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override  /** ��ҳ�汻����ʱ����� */
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override  /** ��ǰ�µ�ҳ�汻ѡ��ʱ���� */
	public void onPageSelected(int arg0) {
		for (int i = 0; i < ids.length; i++) {
			if (arg0 == i) {
				// ����
				dots[i].setImageResource(R.drawable.login_point_selected);
			} else {
				// ����
				dots[i].setImageResource(R.drawable.login_point);
			}
		}
	}

}


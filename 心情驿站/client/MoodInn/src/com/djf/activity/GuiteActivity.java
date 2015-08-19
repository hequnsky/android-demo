package com.djf.activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.djf.callback.OnScrollChangedListener;
import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.util.MoodApplication;
import com.djf.view.FeatureAnimationListener;
import com.djf.view.ObservableScrollView;

public class GuiteActivity extends BaseActivity implements OnGlobalLayoutListener, OnScrollChangedListener    {

	private ObservableScrollView mScrollView;
	private View mAnimView;
	private int mScrollViewHeight;
	private int mStartAnimateTop;
	private Button btnSkip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MoodApplication.getInstance().addActivity(this);
		setContentView(R.layout.guide);
		
		mScrollView = (ObservableScrollView)this.findViewById(R.id.scrollView1);
		mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(this);
		mScrollView.setOnScrollChangedListener(this);
		
		btnSkip=(Button)mScrollView.findViewById(R.id.btnSkip);
		btnSkip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new SharedPreferenceDb(GuiteActivity.this).setIsFirstInstall();
				SkipActivityforClass(GuiteActivity.this, LoginActivity.class);
				finish();
			}
		});
		
		
		
		mAnimView = this.findViewById(R.id.anim1);
		mAnimView.setVisibility(View.INVISIBLE);
	}

	

	@Override
	public void onGlobalLayout() {
		mScrollViewHeight = mScrollView.getHeight();
		mStartAnimateTop = mScrollViewHeight / 3 * 2;
	}

	boolean hasStart = false;
	@Override
	public void onScrollChanged(int top, int oldTop) {
		int animTop = mAnimView.getTop() - top;
		
		if(top > oldTop) {
			if(animTop < mStartAnimateTop && !hasStart) {
				Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.feature_anim2scale_in);
				anim1.setAnimationListener(new FeatureAnimationListener(mAnimView, true));
				
				mAnimView.startAnimation(anim1);
				hasStart = true;
			}
		} else {
			if(animTop > mStartAnimateTop && hasStart) {
				Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.feature_alpha_out);
				anim1.setAnimationListener(new FeatureAnimationListener(mAnimView, false));
				
				mAnimView.startAnimation(anim1);
				hasStart = false;
			}
		}
	}
	

	
	
	
	
}

package com.rmc.gaiya.utils;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.CountDownTimer;

class MyAnimatorUpdateListener implements AnimatorUpdateListener {
	public MyAnimatorUpdateListener(ObjectAnimator animator) {
		this.animator = animator;
	}

	/**
	 * ��ͣ״̬
	 */
	private boolean isPause = false;
	/**
	 * �Ƿ��Ѿ���ͣ�����һ�Ѿ���ͣ����ô�Ͳ���Ҫ�ٴ�����ֹͣ��һЩ�¼��ͼ�������
	 */
	private boolean isPaused = false;

	private boolean isPlay = true;
	/**
	 * ��ǰ�Ķ����Ĳ���λ��
	 */
	private float fraction = 0.0f;
	/**
	 * ��ǰ�����Ĳ�������ʱ��
	 */
	private long mCurrentPlayTime = 0l;

	/**
	 * �Ƿ�����ͣ״̬
	 * 
	 * @return
	 */
	private ObjectAnimator animator;

	public boolean isPause() {
		return isPause;
	}

	public boolean isPlay() {
		return isPlay;
	}

	/**
	 * ֹͣ������ֻ�����ñ�־λ��ʣ��Ĺ��������״̬λ����onAnimationUpdate���в���
	 */
	public void pause() {
		isPause = true;
		isPlay = false;
	}

	public void play() {
		isPause = false;
		isPaused = false;
		isPlay = true;
	}

	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		/**
		 * �������ͣ��״̬������������ÿ��ˢ�¶�����ʱ���ˣ������õ�ǰʱ�䣬�ö���
		 * ��ʱ���ϴ�����ͣ״̬��ͬʱҪ����һ����ֹ��ʱ�������������֤�������ᶶ��
		 */
		if (isPause) {
			if (!isPaused) {
				mCurrentPlayTime = animation.getCurrentPlayTime();
				fraction = animation.getAnimatedFraction();
				animation.setInterpolator(new TimeInterpolator() {
					@Override
					public float getInterpolation(float input) {
						return fraction;
					}
				});
				isPaused = true;
			}
			// ÿ���������ŵ�ʱ�䣬���Ƕ��Ὣ����ʱ�����ص������Ա����²��ŵ�ʱ�����ʹ�����ʱ��,ͬʱҲΪ������������������
			new CountDownTimer(ValueAnimator.getFrameDelay(),
					ValueAnimator.getFrameDelay()) {

				@Override
				public void onTick(long millisUntilFinished) {
				}

				@Override
				public void onFinish() {
					animator.setCurrentPlayTime(mCurrentPlayTime);
				}
			}.start();
		} else {
			// ��ʱ���������ָ������Եģ���������Լ��ģ�Ҳ������������лָ�
			animation.setInterpolator(null);
		}
	}
}
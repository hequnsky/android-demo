package com.bigkoo.svprogresshud;

import com.rmc.gaiya.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sai on 15/9/1.
 */
public class SVCircleProgressBar extends View {
    private Context mContext;
    /**
     * ���ʶ��������
     */
    private Paint paint;

    /**
     * Բ������ɫ
     */
    private int roundColor;

    /**
     * Բ�����ȵ���ɫ
     */
    private int roundProgressColor;

    /**
     * Բ���Ŀ��
     */
    private float roundWidth;

    /**
     * ������
     */
    private int max;

    /**
     * ��ǰ����
     */
    private int progress;

    /**
     * ���ȵķ��ʵ�Ļ��߿���
     */
    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;

    public SVCircleProgressBar(Context context) {
        this(context, null);
        this.mContext = context;

    }

    public SVCircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public SVCircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;

        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.SVCircleProgressBar);

        // ��ȡ�Զ������Ժ�Ĭ��ֵ
        roundColor = mTypedArray.getColor(R.styleable.SVCircleProgressBar_roundColor, Color.BLUE);
        roundProgressColor = mTypedArray.getColor(R.styleable.SVCircleProgressBar_roundProgressColor,
                Color.GRAY);
        roundWidth = mTypedArray.getDimension(R.styleable.SVCircleProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.SVCircleProgressBar_max, 100);
        style = mTypedArray.getInt(R.styleable.SVCircleProgressBar_style, 0);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * �������Ĵ�Բ��
         */
        int centre = getWidth() / 2; // ��ȡԲ�ĵ�x����
        int radius = (int) (centre - roundWidth / 2); // Բ���İ뾶
        paint.setAntiAlias(true); // �������
        paint.setColor(roundColor); // ����Բ������ɫ
        paint.setStyle(Paint.Style.STROKE); // ���ÿ���
        paint.setStrokeWidth(roundWidth); // ����Բ���Ŀ��
        canvas.drawCircle(centre, centre, radius, paint); // ����Բ��


        /**
         * ��Բ�� ����Բ���Ľ���
         */

        // ���ý�����ʵ�Ļ��ǿ���
        paint.setStrokeWidth(roundWidth); // ����Բ���Ŀ��
        paint.setColor(roundProgressColor); // ���ý��ȵ���ɫ
        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius); // ���ڶ����Բ������״�ʹ�С�Ľ���

        switch (style) {
            case STROKE: {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 270, 360 * progress / max, false, paint); // ���ݽ��Ȼ�Բ��
                break;
            }
            case FILL: {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(oval, 270, 360 * progress / max, true, paint); // ���ݽ��Ȼ�Բ��
                break;
            }
        }

    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * ���ý��ȵ����ֵ
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * ��ȡ����.��Ҫͬ��
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * ���ý��ȣ���Ϊ�̰߳�ȫ�ؼ������ڿ��Ƕ��ߵ����⣬��Ҫͬ��
     * ˢ�½������postInvalidate()���ڷ�UI�߳�ˢ��
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

}
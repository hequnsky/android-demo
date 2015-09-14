package com.hq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyWare extends View {
    private List<Wave> wList;
    private boolean isRunning = false;

    public MyWare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyWare(Context context, AttributeSet attrs) {
        super(context, attrs);
        wList = new ArrayList<Wave>();
    }

    public MyWare(Context context) {
        super(context);
    }

    private int colors[] = { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };

    // private void initView() {
    // w = new Wave();
    // w.paint = new Paint();
    // w.paint.setColor(colors[(int) (Math.random() * 4)]);
    // w.paint.setAntiAlias(true);// 抗锯齿
    // // 设置成圆环样式
    // w.paint.setStyle(Style.STROKE);
    // w.radiu = 20;
    // w.paint.setStrokeWidth(w.radiu / 3);
    // // 设置透明度--(0-255)
    // w.paint.setAlpha(255);
    // wList.add(w);
    // }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                add(x, y);
                break;
        }
        return true;
    }

    private void add(int x, int y) {
        //用来判断是否新建新的画笔（新画笔颜色不同）
        if (wList.size() == 0) {
            addPoint2List(x, y);
            /**
             * 第一次启动动画
             */
            isRunning = true;
            handler.sendEmptyMessage(0);
        } else {
            Wave w = wList.get(wList.size() - 1);
            //这里的两个1代表在50毫秒内距离多远进行一次新的圆的绘制（旧的圆还在继续绘制中）
            if (Math.abs(w.startX - x) > 1 || Math.abs(w.startY - y) > 1) {
                addPoint2List(x, y);
            }
        }
    }

    private void addPoint2List(int x, int y) {
        Wave w = new Wave();
        w.startX = x;
        w.startY = y;
        Paint paint = new Paint();
        paint.setColor(colors[(int) (Math.random() * 4)]);
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        w.paint = paint;
        wList.add(w);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //刷新数据
            flushData();
            //对整个界面进行重新绘制
            invalidate();

            if (isRunning) {
                //这里这个50就是刷新频率，如果够50毫秒就进行一次刷新
                handler.sendEmptyMessageDelayed(0, 50);
            }
        }
    };

    /**
     * 刷新数据
     */
    private void flushData() {
        for (int i = 0; i < wList.size(); i++) {
            Wave w = wList.get(i);
            // 1.圆环的半径不断变大
            w.radiu += 5;
            w.paint.setStrokeWidth(w.radiu / 3);
            // 2.透明度不断变暗 --为0的时候停止
            int alpha = w.paint.getAlpha();
            alpha -= 10;
            if (alpha < 5) {
                alpha = 0;
            }
            w.paint.setAlpha(alpha);
            if (alpha == 0) {
                //移除看不到的还在扩散的圆
                wList.remove(w);
                continue;
            }
            // 让ondraw执行
//			invalidate();
        }
        if (wList.size() == 0) {
            isRunning = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        // if (w.startX != 0 && startY != 0) {
        // if (paint.getAlpha() > 0) {
        // canvas.drawCircle(startX, startY, radiu, paint);
        // handler.sendEmptyMessageDelayed(0, 100);
        // }
        // }
        for (int i = 0; i < wList.size(); i++) {
            Wave w = wList.get(i);
            canvas.drawCircle(w.startX, w.startY, w.radiu, w.paint);
        }
    }
}

class Wave {
    int startX;
    int startY;
    int radiu;
    Paint paint;
}

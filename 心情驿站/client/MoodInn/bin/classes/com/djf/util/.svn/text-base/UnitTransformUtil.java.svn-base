package com.djf.util;

import android.content.Context;
/**
 * 像素和dp之间的互转
 * @author Administrator
 *
 */
public class UnitTransformUtil {

	/** * 根据手机的分辨率从dp 的单位 转成为px(像素) */ 
    public static int dip2px(Context context, float dpValue) { 
            final float scale = context.getResources().getDisplayMetrics().density; 
            return (int) (dpValue * scale + 0.5f); 
    } 

    /** * 根据手机的分辨率从px(像素) 的单位 转成为dp */ 
    public static int px2dip(Context context, float pxValue) { 
            final float scale = context.getResources().getDisplayMetrics().density; 
            return (int) (pxValue / scale + 0.5f); 
    } 
}

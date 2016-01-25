package com.rmc.gaiya.utils;

import android.content.Context;
import android.content.SharedPreferences;
 
/**
 * SharedPreference方式持久化数据的工具类
 * 
 * @author hq
 * @version 1.0
 */
public class SharedPreferenceUtil {
 
    /**
     * 保存键值对
     * 
     * @param context 上下文
     * @param fileName 文件名
     * @param key 键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean set(Context context, String fileName, String key,
            String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }
 
    /**
     * 获得键对应的值,如果没有则返回""
     * 
     * @param context 上下文
     * @param fileName 文件名
     * @param key 键
     * @return 值，没有则返回""
     */
    public static String get(Context context, String fileName, String key) {
        return get(context, fileName, key, "");
    }
 
    /**
     * 获得键对应的值,如果没有则返回defaultValue
     * 
     * @param context 上下文
     * @param fileName 文件名
     * @param key 键
     * @param defaultValue 默认值
     * @return 值，没有则返回defaultValue
     */
    public static String get(Context context, String fileName, String key,
            String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defaultValue);// 第二个参数为默认值
        return value;
    }
 
    /**
     * 移除一项
     * @param context 上下文
     * @param fileName 文件名
     * @param key 键
     * @return 是否移除成功
     */
    public static boolean remove(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
 
    }
     
    /**
     * 清除文件内容
     * @param context 上下文
     * @param fileName 文件名
     * @return 是否清除成功
     */
    public static boolean clear(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
 
    }
     
    /**
     * 某一项是否存在
     * @param context 上下文
     * @param fileName 文件名
     * @param key 键
     * @return 该键对应的值是否存在
     */
    public static boolean contatins(Context context, String fileName,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
         
 
    }
 
}

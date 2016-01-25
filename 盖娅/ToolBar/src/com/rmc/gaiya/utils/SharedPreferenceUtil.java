package com.rmc.gaiya.utils;

import android.content.Context;
import android.content.SharedPreferences;
 
/**
 * SharedPreference��ʽ�־û����ݵĹ�����
 * 
 * @author hq
 * @version 1.0
 */
public class SharedPreferenceUtil {
 
    /**
     * �����ֵ��
     * 
     * @param context ������
     * @param fileName �ļ���
     * @param key ��
     * @param value ֵ
     * @return �Ƿ񱣴�ɹ�
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
     * ��ü���Ӧ��ֵ,���û���򷵻�""
     * 
     * @param context ������
     * @param fileName �ļ���
     * @param key ��
     * @return ֵ��û���򷵻�""
     */
    public static String get(Context context, String fileName, String key) {
        return get(context, fileName, key, "");
    }
 
    /**
     * ��ü���Ӧ��ֵ,���û���򷵻�defaultValue
     * 
     * @param context ������
     * @param fileName �ļ���
     * @param key ��
     * @param defaultValue Ĭ��ֵ
     * @return ֵ��û���򷵻�defaultValue
     */
    public static String get(Context context, String fileName, String key,
            String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defaultValue);// �ڶ�������ΪĬ��ֵ
        return value;
    }
 
    /**
     * �Ƴ�һ��
     * @param context ������
     * @param fileName �ļ���
     * @param key ��
     * @return �Ƿ��Ƴ��ɹ�
     */
    public static boolean remove(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
 
    }
     
    /**
     * ����ļ�����
     * @param context ������
     * @param fileName �ļ���
     * @return �Ƿ�����ɹ�
     */
    public static boolean clear(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
 
    }
     
    /**
     * ĳһ���Ƿ����
     * @param context ������
     * @param fileName �ļ���
     * @param key ��
     * @return �ü���Ӧ��ֵ�Ƿ����
     */
    public static boolean contatins(Context context, String fileName,String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
         
 
    }
 
}

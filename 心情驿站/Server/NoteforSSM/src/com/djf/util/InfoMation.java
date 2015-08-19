package com.djf.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;




public class InfoMation {

	public static String phone="18301742267";
	public static String password="123";
	public static String name="jackd";
	public static String id="123";
	public static String oldpassword="123";
	public static String newpassword="456";
	
	public static String str=id+oldpassword+newpassword;
	
	//算法密匙
    private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };  
    
    /** 
     * 数据加密，算法（DES） 
     * 
     * @param data 
     *            要进行加密的数据 
     * @return 加密后的数据 
     */  
    public static String encryptBasedDes(String data) {  
        String encryptedData = null;  
        try {  
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            DESKeySpec deskey = new DESKeySpec(DES_KEY);  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(deskey);  
            // 加密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
            // 加密，并把字节数组编码成字符串  
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));  
        } catch (Exception e) {  
//            log.error("加密错误，错误信息：", e);  
            throw new RuntimeException("加密错误，错误信息：", e);  
        }  
        return encryptedData;  
    }  
	
    /**
     * 解密
     * @param cryptData
     * @return
     */
    public static String decryptBasedDes(String cryptData) {  
        String decryptedData = null;  
        try {  
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();  
            DESKeySpec deskey = new DESKeySpec(DES_KEY);  
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(deskey);  
            // 解密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.DECRYPT_MODE, key, sr);  
            // 把字符串解码为字节数组，并解密  
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));  
        } catch (Exception e) {  
//            log.error("解密错误，错误信息：", e);  
            throw new RuntimeException("解密错误，错误信息：", e);  
        }  
        return decryptedData;  
    }  
    
    public static void main(String[] args) {
		
    	String str1="12014-11-15";
    	 // DES数据加密  
//        String s1=encryptBasedDes(str);
    	String s1=encryptBasedDes(str1);
          
        System.out.println("加密后"+s1);  
          
        // DES数据解密  
        String s2=decryptBasedDes(s1);  
          
        System.err.println("解密后"+s2);  
	}
    
}

package com.github.xphsc.encrypt;


import java.util.HashMap;
import java.util.Map;


public class EncryptAndDecryptUtil {


	public static String md5Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Util.encrypt(value, MD5Util.MD5_KEY);
		}
		return result;
	}


	public static String shaEncrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Util.encrypt(value, MD5Util.SHA_KEY);
		}
		return result;
	}


	public static String base64Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  Base64Util.encrypt(value.getBytes());
		}
		return result;

	}


	public static String base64Decrypt(String value){
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){
				byte[] bytes = Base64Util.decrypt(value);
				result = new String(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String desEncrypt(String value,String key){
		key = key == null ? DESUtil.KEY : key;
		String result = null;

		try {
			if(value != null && !"".equals(value.trim())){
				result = DESUtil.encrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String desDecrypt(String value,String key){
		key = key == null ? DESUtil.KEY : key;
		String result = null;

		try {
			if(value != null && !"".equals(value.trim())){
				result =  DESUtil.decrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String aesEncrypt(String value,String key ){
		key = key == null ? AESUtil.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtil.encrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public static String aesDecrypt(String value , String key){
		key = key == null ? AESUtil.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtil.decrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public static void main(String[] args)  {
		System.out.println(md5Encrypt("111111"));
	}

}

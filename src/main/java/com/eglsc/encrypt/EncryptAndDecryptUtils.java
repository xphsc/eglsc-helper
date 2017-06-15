package com.eglsc.encrypt;


import java.util.HashMap;
import java.util.Map;


public class EncryptAndDecryptUtils {


	public static String md5Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Utils.encrypt(value,MD5Utils.MD5_KEY);
		}
		return result;
	}


	public static String shaEncrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Utils.encrypt(value,MD5Utils.SHA_KEY);
		}
		return result;
	}


	public static String base64Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  Base64Utils.encrypt(value.getBytes());
		}
		return result;

	}

	/**
	 * BASE64 ����
	 * @param value
	 * 				�������ַ���
	 * @return
	 */
	public static String base64Decrypt(String value){
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){
				byte[] bytes = Base64Utils.decrypt(value);
				result = new String(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String desEncrypt(String value,String key){
		key = key == null ? DESUtils.KEY : key;
		String result = null;

		try {
			if(value != null && !"".equals(value.trim())){
				result = DESUtils.encrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String desDecrypt(String value,String key){
		key = key == null ? DESUtils.KEY : key;
		String result = null;

		try {
			if(value != null && !"".equals(value.trim())){
				result =  DESUtils.decrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String aesEncrypt(String value,String key ){
		key = key == null ? AESUtils.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtils.encrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public static String aesDecrypt(String value , String key){
		key = key == null ? AESUtils.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtils.decrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static void main(String[] args)  {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "1233");
	   System.out.println(md5Encrypt("appid"));
		System.out.println(shaEncrypt("appid"));
		System.out.println(base64Encrypt("appid"));
		System.out.println(desEncrypt("appid", "q1234ee5wwee"));
		System.out.println(aesEncrypt("appid", "q1234ee5wwee"));
		System.out.println(base64Decrypt("8019D4BF3AAAB24AE34C66BE1101B741E9F4D22A"));


	}
}

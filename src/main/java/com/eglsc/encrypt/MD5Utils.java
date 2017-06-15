package com.eglsc.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


class MD5Utils {
	protected final static String MD5_KEY = "MD5";

	protected final static String SHA_KEY = "SHA1";

	protected static String encrypt(String value,String key) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(key);

			byte[] inputByteArray = value.getBytes();
			messageDigest.update(inputByteArray);
			byte[] resultByteArray = messageDigest.digest();
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		char[] resultCharArray = new char[byteArray.length * 2];
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}

	public static void main(String[] args)  {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "1233");
		System.out.println(encrypt("2222","eerr"));
	}
}

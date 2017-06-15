package com.eglsc.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class SignHelper {public static String sign(String secret, Map<String, String> params) {
    // 先将参数以其参数名的字典序升序进行排序
    Map<String, String> sortedParams = new TreeMap<String, String>(params);
    Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

    // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
    StringBuilder basestring = new StringBuilder();
    for (Map.Entry<String, String> param : entrys) {
        basestring.append(param.getKey()).append("=").append(param.getValue());
    }
    basestring.append(secret);

    // 使用MD5对待签名串求签
    byte[] bytes = null;
    try {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
    } catch (GeneralSecurityException ex) {
        ex.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    // 将MD5输出的二进制结果转换为小写的十六进制
    StringBuilder sign = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(bytes[i] & 0xFF);
        if (hex.length() == 1) {
            sign.append("0");
        }
        sign.append(hex);
    }
    return sign.toString();
}

    public static boolean verifySign(String secret, String sign, Map<String, String> params) {
        return sign(secret, params).equals(sign);
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", "1233");
        System.out.println(SignHelper.sign("aa",params));
    }


}

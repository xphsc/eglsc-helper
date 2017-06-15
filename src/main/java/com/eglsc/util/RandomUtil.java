package com.eglsc.util;

import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class RandomUtil {
    private static final String BASE_NUMBER = "0123456789";
    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    private static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

    private RandomUtil() {
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static int randomInt() {
        Random random = new Random();
        return random.nextInt();
    }

    public static int randomInt(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    public static byte[] randomBytes(int length) {
        Random random = new Random();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    public static <T> T randomEle(List<T> list) {
        return randomEle(list, list.size());
    }

    public static <T> T randomEle(List<T> list, int limit) {
        return list.get(randomInt(limit));
    }

    public static <T> List<T> randomEles(List<T> list, int count) {
        ArrayList result = new ArrayList(count);
        int limit = list.size();

        while(true) {
            --count;
            if(count <= 0) {
                return result;
            }

            result.add(randomEle(list, limit));
        }
    }

    public static <T> Set<T> randomEleSet(Collection<T> collection, int count) {
        ArrayList source = new ArrayList(new HashSet(collection));
        if(count > source.size()) {
            throw new IllegalArgumentException("Count is larger than collection distinct size !");
        } else {
            HashSet result = new HashSet(count);
            int limit = collection.size();

            while(result.size() <= count) {
                result.add(randomEle(source, limit));
            }

            return result;
        }
    }

    public static String randomString(int length) {
        return randomString("abcdefghijklmnopqrstuvwxyz0123456789", length);
    }

    public static String randomNumbers(int length) {
        return randomString("0123456789", length);
    }

    public static String randomString(String baseString, int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        if(length < 1) {
            length = 1;
        }

        int baseLength = baseString.length();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }

        return sb.toString();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

}

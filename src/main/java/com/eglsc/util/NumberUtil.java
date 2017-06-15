package com.eglsc.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class NumberUtil   extends NumberUtils {
    public NumberUtil() {
    }

    public static int getInt(String data, int defaultValue) {
        int ret;
        try {
            ret = Integer.parseInt(data);
        } catch (NumberFormatException var4) {
            ret = defaultValue;
        }

        return ret;
    }

    public static int getInt(String data) {
        return getInt(data, 0);
    }

    public static double getDouble(String data) {
        double ret;
        try {
            ret = Double.parseDouble(data);
        } catch (NumberFormatException var4) {
            ret = 0.0D;
        }

        return ret;
    }

    public static List<Integer> getInts(String data) {
        return getInts(data, ",");
    }

    public static List<Integer> getInts(String data, String space) {
        ArrayList ints = new ArrayList();
        String[] intArray = StringUtil.list(data, space);
        if(null != intArray && intArray.length != 0) {
            String[] var4 = intArray;
            int var5 = intArray.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String intString = var4[var6];

                try {
                    ints.add(Integer.valueOf(Integer.parseInt(intString)));
                } catch (NumberFormatException var9) {
                    ;
                }
            }

            return ints;
        } else {
            return ints;
        }
    }

    public static int[] getIntArray(String data) {
        return getIntArray(data, ",");
    }

    public static int[] getIntArray(String data, String space) {
        List ints = getInts(data, space);
        int[] ret = new int[ints.size()];

        for(int i = 0; i < ints.size(); ++i) {
            ret[i] = ((Integer)ints.get(i)).intValue();
        }

        return ret;
    }



}

package com.eglsc.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class EscapeUtil {

    private EscapeUtil() {
    }

    public static String escape(String content) {
        if(StringUtils.isBlank(content)) {
            return content;
        } else {
            StringBuilder tmp = new StringBuilder();
            tmp.ensureCapacity(content.length() * 6);

            for(int i = 0; i < content.length(); ++i) {
                char j = content.charAt(i);
                if(!Character.isDigit(j) && !Character.isLowerCase(j) && !Character.isUpperCase(j)) {
                    if(j < 256) {
                        tmp.append("%");
                        if(j < 16) {
                            tmp.append("0");
                        }

                        tmp.append(Integer.toString(j, 16));
                    } else {
                        tmp.append("%u");
                        tmp.append(Integer.toString(j, 16));
                    }
                } else {
                    tmp.append(j);
                }
            }

            return tmp.toString();
        }
    }

    public static String unescape(String content) {
        if(StringUtils.isBlank(content)) {
            return content;
        } else {
            StringBuilder tmp = new StringBuilder(content.length());
            int lastPos = 0;
            boolean pos = false;

            while(lastPos < content.length()) {
                int pos1 = content.indexOf("%", lastPos);
                if(pos1 == lastPos) {
                    char ch;
                    if(content.charAt(pos1 + 1) == 117) {
                        ch = (char)Integer.parseInt(content.substring(pos1 + 2, pos1 + 6), 16);
                        tmp.append(ch);
                        lastPos = pos1 + 6;
                    } else {
                        ch = (char)Integer.parseInt(content.substring(pos1 + 1, pos1 + 3), 16);
                        tmp.append(ch);
                        lastPos = pos1 + 3;
                    }
                } else if(pos1 == -1) {
                    tmp.append(content.substring(lastPos));
                    lastPos = content.length();
                } else {
                    tmp.append(content.substring(lastPos, pos1));
                    lastPos = pos1;
                }
            }

            return tmp.toString();
        }
    }

    public static String safeUnescape(String content) {
        try {
            return unescape(content);
        } catch (Exception var2) {
            return content;
        }
    }
}

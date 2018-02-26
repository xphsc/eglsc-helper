package com.github.xphsc.lang;



import com.github.xphsc.lang.i18n.LocaleUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 字符串转义工具类。
 * Created by ${huipei.x} on 2017-5-31.
 */
public class StringEscapes {

    public static String unescapeURL(String str) {
        try {
            return unescapeURLInternal(str, null);
        } catch (UnsupportedEncodingException e) {
            return str; // 不可能发生这个异常
        }
    }

    private static String unescapeURLInternal(String str, String encoding) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }

        try {
            StringBuilder out = new StringBuilder(str.length());

            if (unescapeURLInternal(str, encoding, out)) {
                return out.toString();
            }

            return str;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            return str; // StringBuilder不可能发生这个异常
        }
    }

    private static boolean unescapeURLInternal(String str, String encoding, Appendable out) throws IOException {
        if (encoding == null) {
            encoding = LocaleUtil.getContext().getCharset().name();
        }
        boolean needToChange = false;

        if (out == null) {
            throw new IllegalArgumentException("The Appendable must not be null");
        }

        byte[] buffer = null;
        int pos = 0;
        int startIndex = 0;

        char[] charArray = str.toCharArray();
        int length = charArray.length;

        for (int i = 0; i < length; i++) {
            int ch = charArray[i];

            if (ch < 256) {
                // 读取连续的字节，并将它按指定编码转换成字符。
                if (buffer == null) {
                    buffer = new byte[length - i]; // 最长只需要length - i
                }

                if (pos == 0) {
                    startIndex = i;
                }

                switch (ch) {
                    case '+':

                        // 将'+'转换成' '
                        buffer[pos++] = ' ';

                        // 设置改变标志
                        needToChange = true;
                        break;

                    case '%':

                        if (i + 2 < length) {
                            try {
                                byte b = (byte) Integer.parseInt(str.substring(i + 1, i + 3), 16);

                                buffer[pos++] = b;
                                i += 2;

                                // 设置改变标志
                                needToChange = true;
                            } catch (NumberFormatException e) {
                                // 如果%xx不是合法的16进制数，则原样输出
                                buffer[pos++] = (byte) ch;
                            }
                        } else {
                            buffer[pos++] = (byte) ch;
                        }

                        break;

                    default:

                        // 写到bytes中，到时一起输出。
                        buffer[pos++] = (byte) ch;
                        break;
                }
                continue;
            }
            // 先将buffer中的字节串转换成字符串。
            if (pos > 0) {
                String s = new String(buffer, 0, pos, encoding);

                out.append(s);

                if (!needToChange && !s.equals(new String(charArray, startIndex, pos))) {
                    needToChange = true;
                }

                pos = 0;
            }

            // 如果ch是ISO-8859-1以外的字符，直接输出即可
            out.append((char) ch);
        }

        // 先将buffer中的字节串转换成字符串。
        if (pos > 0) {
            String s = new String(buffer, 0, pos, encoding);

            out.append(s);

            if (!needToChange && !s.equals(new String(charArray, startIndex, pos))) {
                needToChange = true;
            }

            pos = 0;
        }

        return needToChange;
    }
}

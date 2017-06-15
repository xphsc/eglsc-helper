package com.eglsc.util;


import com.eglsc.lang.StrFormatter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {

    /**
     * 字符连接符
     */
    private static final char SEPARATOR = '_';
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 转换为字节数组
     *
     * @param str 字符串
     * @return byte[] byte [ ]
     */
    public static byte[] getBytes(String str) {
        if (str == null) {
            throw new NullPointerException("str cannot be null");
        }
        return str.getBytes(DEFAULT_CHARSET);
    }

    /**
     * 转换为字节数组
     *
     * @param bytes 字节数组
     * @return String string
     */
    public static String toString(byte[] bytes) {
        return new String(bytes, DEFAULT_CHARSET);
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true boolean
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     *
     * @param html the html
     * @return the string
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        String ls = s.toLowerCase();

        StringBuilder sb = new StringBuilder(ls.length());
        boolean upperCase = false;
        for (int i = 0; i < ls.length(); i++) {
            char c = ls.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        String cs = toCamelCase(s);
        return cs.substring(0, 1).toUpperCase() + cs.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Trim to default string.
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return String string
     */
    public static String trimToDefault(final String str, String defaultValue) {
        final String ts = trim(str);
        return isEmpty(ts) ? defaultValue : ts;
    }

    public static String removePrefix(String str, String prefix) {
        return !isEmpty(str) && !isEmpty(prefix)?(str.startsWith(prefix)?StringUtils.substring(str, prefix.length()):str):str;
    }

    public static String removePrefixIgnoreCase(String str, String prefix) {
        return !isEmpty(str) && !isEmpty(prefix)?(str.toLowerCase().startsWith(prefix.toLowerCase())?StringUtils.substring(str, prefix.length()):str):str;
    }
    public static String subPre(String string, int toIndex) {
        return StringUtils.substring(string, 0, toIndex);
    }
    public static String removeSuffix(String str, String suffix) {
        return !isEmpty(str) && !isEmpty(suffix)?(str.endsWith(suffix)?subPre(str, str.length() - suffix.length()):str):str;
    }
    public static String format(String template, Object... params) {
        return !ArrayUtils.isEmpty(params) && !isBlank(template)? StrFormatter.format(template, params):template;
    }

    public static boolean hasBlank(CharSequence... strs) {
        if(ArrayUtil.isEmpty(strs)) {
            return true;
        } else {
            CharSequence[] arr$ = strs;
            int len$ = strs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                CharSequence str = arr$[i$];
                if(isBlank(str)) {
                    return true;
                }
            }

            return false;
        }
    }
    public static String str(Object obj, Charset charset) {
        return null == obj?null:(obj instanceof String?(String)obj:(obj instanceof byte[]?str((byte[])((byte[])obj), charset):(obj instanceof Byte[]?str((Byte[])((Byte[])obj), charset):(obj instanceof ByteBuffer ?str((ByteBuffer)obj, charset):(ArrayUtil.isArray(obj)?ArrayUtils.toString(obj):obj.toString())))));
    }
    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }

    public static String[] list(String content, String space) {
        return null != content?content.split(space):new String[0];
    }

    public static String[] list(String content) {
        return list(content, ",");
    }
}

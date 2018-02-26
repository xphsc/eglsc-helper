package com.github.xphsc.lang.i18n;

import com.github.xphsc.lang.Emptys;
import com.github.xphsc.util.StringUtil;

import java.util.Locale;

/**
 * 用来处理地域和字符编码的工具类。
 * Created by ${huipei.x} on 2017-5-31.
 */
public class LocaleUtil {

    private static final LocaleInfo systemLocaleInfo = new LocaleInfo();
    private static LocaleInfo defaultLocalInfo = systemLocaleInfo;
    private static final ThreadLocal<LocaleInfo> contextLocaleInfoHolder = new ThreadLocal<LocaleInfo>();

    public static LocaleInfo getContext() {
        LocaleInfo contextLocaleInfo = contextLocaleInfoHolder.get();
        return contextLocaleInfo == null ? getDefault() : contextLocaleInfo;
    }

    public static LocaleInfo getDefault() {
        return defaultLocalInfo == null ? systemLocaleInfo : defaultLocalInfo;
    }

    public static Locale parseLocale(String localeString) {
        localeString = StringUtil.trimToNull(localeString);

        if (localeString == null) {
            return null;
        }

        String language = Emptys.EMPTY_STRING;
        String country = Emptys.EMPTY_STRING;
        String variant = Emptys.EMPTY_STRING;

        // language
        int start = 0;
        int index = localeString.indexOf("_");

        if (index >= 0) {
            language = localeString.substring(start, index).trim();

            // country
            start = index + 1;
            index = localeString.indexOf("_", start);

            if (index >= 0) {
                country = localeString.substring(start, index).trim();

                // variant
                variant = localeString.substring(index + 1).trim();
            } else {
                country = localeString.substring(start).trim();
            }
        } else {
            language = localeString.substring(start).trim();
        }

        return new Locale(language, country, variant);
    }
}
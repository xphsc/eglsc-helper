package com.github.xphsc.lang.i18n;

import com.github.xphsc.lang.Validator;
import com.github.xphsc.util.StringUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

/**
 *Created by ${huipei.x} on 2017-5-31.
 */
public class LocaleInfo implements Cloneable, Externalizable {

    /**
     *
     */
    private static final long serialVersionUID = 4862106896152533673L;

    private Locale locale;
    private Charset charset;

    public static LocaleInfo parse(String name) {
        name = Validator.notEmpty(StringUtil.trimToNull(name), "no locale name");
        int index = name.indexOf(":");
        String localePart = name;
        String charsetPart = null;

        if (index >= 0) {
            localePart = name.substring(0, index);
            charsetPart = name.substring(index + 1);
        }

        Locale locale = LocaleUtil.parseLocale(localePart);
        String charset = StringUtil.trimToNull(charsetPart);

        return new LocaleInfo(locale, charset);
    }

    public LocaleInfo() {
        this.locale = Validator.notNull(Locale.getDefault(), "system locale");
        this.charset = Validator.notNull(Charset.defaultCharset(), "system charset");
    }

    public LocaleInfo(Locale locale) {
        this(locale, null, LocaleUtil.getDefault());
    }
    public LocaleInfo(Locale locale, String charset) {
        this(locale, charset, LocaleUtil.getDefault());
    }

    LocaleInfo(Locale locale, String charset, LocaleInfo fallbackLocaleInfo) {
        Validator.notNull(fallbackLocaleInfo, "fallbackLocaleInfo");
        charset = StringUtil.trimToNull(charset);

        if (locale == null) {
            locale = fallbackLocaleInfo.getLocale();

            if (charset == null) {
                charset = fallbackLocaleInfo.getCharset().name();
            }
        } else {
            if (charset == null) {
                charset = "UTF-8"; // 如果指定了locale，但未指定charset，则使用万能的UTF-8
            }
        }

        this.locale = locale;

        try {
            this.charset = Charset.forName(charset);
        } catch (UnsupportedCharsetException e) {
            this.charset = new UnknownCharset(charset);
        }
    }


    public Locale getLocale() {
        return locale;
    }

    public Charset getCharset() {
        return charset;
    }

    public boolean isCharsetSupported() {
        return !(charset instanceof UnknownCharset);
    }

    public LocaleInfo assertCharsetSupported() throws UnsupportedCharsetException {
        if (charset instanceof UnknownCharset) {
            throw new UnsupportedCharsetException(charset.name());
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof LocaleInfo)) {
            return false;
        }

        LocaleInfo otherLocaleInfo = (LocaleInfo) o;

        return locale.equals(otherLocaleInfo.locale) && charset.equals(otherLocaleInfo.charset);
    }

    @Override
    public int hashCode() {
        return charset.hashCode() * 31 + locale.hashCode();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(toString());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        LocaleInfo info = parse(in.readUTF());

        locale = info.getLocale();
        charset = info.getCharset();
    }

    @Override
    public String toString() {
        return locale + ":" + charset;
    }

    static class UnknownCharset extends Charset {
        public UnknownCharset(String name) {
            super(Validator.notNull(name, "charset name"), null);
        }

        @Override
        public boolean contains(Charset cs) {
            return false;
        }

        @Override
        public CharsetDecoder newDecoder() {
            return null;
        }

        @Override
        public CharsetEncoder newEncoder() {
            return null;
        }
    }

}

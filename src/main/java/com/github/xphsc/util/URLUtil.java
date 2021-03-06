package com.github.xphsc.util;

import com.github.xphsc.lang.Assert;
import com.sun.xml.internal.ws.util.UtilException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class URLUtil {

    private URLUtil() {
    }

    public static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException var2) {
            throw new UtilException(var2.getMessage(), var2);
        }
    }

    public static URL getURL(String pathBaseClassLoader) {
        return ClassUtil.getClassLoader().getResource(pathBaseClassLoader);
    }

    public static URL getURL(String path, Class<?> clazz) {
        return clazz.getResource(path);
    }

    public static URL getURL(File file) {
        Assert.notNull(file, "File is null !");

        try {
            return file.toURI().toURL();
        } catch (MalformedURLException var2) {
            throw new UtilException("Error occured when get URL!", var2);
        }
    }

    public static URL[] getURLs(File... files) {
        URL[] urls = new URL[files.length];

        try {
            for(int e = 0; e < files.length; ++e) {
                urls[e] = files[e].toURI().toURL();
            }

            return urls;
        } catch (MalformedURLException var3) {
            throw new UtilException("Error occured when get URL!", var3);
        }
    }

    public static String formatUrl(String url) {
        return StringUtil.isBlank(url)?null:(!url.startsWith("http://") && !url.startsWith("https://")?"http://" + url:url);
    }

    public static String complateUrl(String baseUrl, String relativePath) {
        baseUrl = formatUrl(baseUrl);
        if(StringUtil.isBlank(baseUrl)) {
            return null;
        } else {
            try {
                URL e = new URL(baseUrl);
                URL parseUrl = new URL(e, relativePath);
                return parseUrl.toString();
            } catch (MalformedURLException var4) {
                throw new UtilException(var4);
            }
        }
    }

    public static String encode(String url, String charset) {
        try {
            return URLEncoder.encode(url, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new UtilException(var3);
        }
    }

    public static String decode(String url, String charset) {
        try {
            return URLDecoder.decode(url, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new UtilException(var3);
        }
    }

    public static String getPath(String uriStr) {
        URI uri = null;

        try {
            uri = new URI(uriStr);
        } catch (URISyntaxException var3) {
            throw new UtilException(var3);
        }

        return uri == null?null:uri.getPath();
    }

    public static URI toURI(URL url) {
        return toURI(url.toString());
    }

    public static URI toURI(String location) {
        try {
            return new URI(location.replace(" ", "%20"));
        } catch (URISyntaxException var2) {
            throw new UtilException(var2);
        }
    }
}

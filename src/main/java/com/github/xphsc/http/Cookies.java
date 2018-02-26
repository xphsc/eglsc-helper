package com.github.xphsc.http;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Cookies {

    /**
     * 根据name读取cookie的值
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        if(name == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据name读取cookie的值，支持多个相同cookie name的情况
     * @param request
     * @param name
     * @return
     */
    public static List<String> getCookieValues(HttpServletRequest request, String name) {
        if(name == null) {
            return null;
        }
        List<String> cookieList = new ArrayList<String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())) {
                    try {
                        cookieList.add(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        cookieList.add(cookie.getValue());
                    }
                }
            }
        }
        return cookieList;
    }

    /**
     * 增加cookie，如果name相同的话，浏览器会把cookie值追加要已有的之上
     *
     * @param response
     * @param name cookie名字
     * @param value cookie值，不建议为null值
     * @param domain 指定域名，null表示不指定
     * @param expireSeconds cookie生命周期 以秒为单位，当设置为0时，cookie默认有效期100年；如果删除，请用removeCookie方法
     */
    public static void addCookie(HttpServletResponse response, String name, String value,
                                 String domain, int expireSeconds) {
        try {
            value = value == null ? null : URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if(domain != null) {
            cookie.setDomain(domain);
        }
        if (expireSeconds == 0) {
            cookie.setMaxAge(100 * 365 * 24 * 3600);
        } else {
            cookie.setMaxAge(expireSeconds);
        }
        response.addCookie(cookie);
    }

    /**
     * 增加cookie，如果name相同的话，浏览器会把cookie值追加要已有的之上。有效期100年。
     * @param response
     * @param name
     * @param value
     * @param domain
     */
    public static void addCookie(HttpServletResponse response, String name, String value,
                                 String domain) {
        addCookie(response, name, value, domain, 0);
    }

    /**
     * 删除cookie
     * @param response
     * @param name
     * @param domain 当为null时表示不指定
     */
    public static void removeCookie(HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        if(domain != null) {
            cookie.setDomain(domain);
        }
        List<String> list=new ArrayList<>();
        cookie.setMaxAge(0); // delete
        response.addCookie(cookie);
    }

}

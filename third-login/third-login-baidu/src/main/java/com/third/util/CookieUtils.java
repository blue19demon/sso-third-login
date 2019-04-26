package com.third.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private static final String COOKIE_KEY = "LOGIN_COOKIE_KEY";

	public static String getCookie(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				String name = cookie.getName();
				if (COOKIE_KEY.equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static String removeCookie(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				String name = cookie.getName();
				if (COOKIE_KEY.equals(name)) {
					Cookie coo= new Cookie(name, "");
					coo.setPath("/");
					coo.setMaxAge(0);// 设置保存cookie最大时长为0，即使其失效
					resp.addCookie(coo);
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static String setCookie(HttpServletRequest req, HttpServletResponse resp) {
		String uuid = UUID.randomUUID().toString();
		Cookie cookie = new Cookie(COOKIE_KEY, uuid);
		cookie.setDomain("auth.com");
		resp.addCookie(cookie);
		return uuid;
	}

}

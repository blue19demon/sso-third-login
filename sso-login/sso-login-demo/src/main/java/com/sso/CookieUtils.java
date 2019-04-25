package com.sso;

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

	public static String setCookie(HttpServletRequest req, HttpServletResponse resp) {
		String uuid = UUID.randomUUID().toString();
		Cookie cookie = new Cookie(COOKIE_KEY, uuid);
		resp.addCookie(cookie);
		return uuid;
	}

}
